package customClasses;

import java.io.File;
import java.io.FileOutputStream;

import javax.swing.*;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import models.Client;
import views.ClientView;

public class PdfGenerate 
{
	public PdfGenerate()
	{
	}
	
	public void pdfGenerating(Client c)
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Generar PDF");
		
		int userSelection = fileChooser.showSaveDialog(null);
		if(userSelection != JFileChooser.APPROVE_OPTION)
		{
			return;
		}
		
		File saveFile = fileChooser.getSelectedFile();
		if(!saveFile.getAbsolutePath().endsWith(".pdf"))
		{
			saveFile = new File(saveFile.getAbsolutePath() + ".pdf");
		}
		Document doc = new Document();
		
		try
		{
			PdfWriter.getInstance(doc, new FileOutputStream(saveFile));
			doc.open();
			
			PdfPTable table = new PdfPTable(2);
			table.setWidthPercentage(100);
			table.setWidths(new float[] {1, 4});
			
			Image img = Image.getInstance(ClientView.class.getResource("/images/elManglarLogo.png"));
			img.scaleToFit(110, 110);
			PdfPCell imgCell = new PdfPCell(img, false);
			imgCell.setBorder(Rectangle.NO_BORDER);
			imgCell.setRowspan(2);
			table.addCell(imgCell);
			
			PdfPCell txtCell = new PdfPCell();
			txtCell.setBorder(Rectangle.NO_BORDER);
			
			Font title = new Font(Font.FontFamily.HELVETICA, 42, Font.BOLD, new BaseColor(36, 78, 35));
			Paragraph head = new Paragraph("EL MANGLAR", title);
			head.setAlignment(Element.ALIGN_CENTER);
			
			Font subtitle = new Font(Font.FontFamily.HELVETICA, 22, Font.NORMAL, new BaseColor(36, 78, 35));
			Paragraph sub = new Paragraph("Informacion del cliente", subtitle);
			sub.setAlignment(Element.ALIGN_CENTER);
			sub.setSpacingAfter(20);
			
			txtCell.addElement(head);
			txtCell.addElement(sub);
			table.addCell(txtCell);
			
			doc.add(table);
			doc.add(Chunk.NEWLINE);
			
			Font text = new Font(Font.FontFamily.HELVETICA, 15, Font.NORMAL, new BaseColor(36, 78, 35));
			String datos = String.format(
					"\n" + java.time.LocalDate.now() + "\n" +
					"Nombre completo: " + c.name + " " +  c.last_Name + 
					"\nNumero de telefono: " + c.phone_Number +
					"\nDireccion principal: " + c.address_1 +
					"\nDireccion segundaria: " + c.address_2 +
					"\nCiudad: " + c.city +
					"\nEstado: " + c.state +
					"\nCodigo postal: " + c.postal_Code +
					"\nCorroe electronico: " + c.email);
			doc.add(new Paragraph(datos, text));
			
			OptionPaneButton optionPane = new OptionPaneButton("Descarga", "Descarga exitosa.");
			optionPane.checkOptionPane();
		} catch (Exception e) {
			e.printStackTrace();
			OptionPaneButton errorPane = new OptionPaneButton("Descarga", "Error al descargar.");
			errorPane.checkOptionPane();
		} finally {
			doc.close();
		}
	}
}
