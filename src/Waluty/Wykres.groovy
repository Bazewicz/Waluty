package Waluty

import javax.swing.JFrame
import java.awt.Graphics2D
import java.awt.geom.Rectangle2D
import com.lowagie.text.Document
import com.lowagie.text.DocumentException
import com.lowagie.text.Rectangle
import com.lowagie.text.pdf.DefaultFontMapper
import com.lowagie.text.pdf.PdfContentByte
import com.lowagie.text.pdf.PdfTemplate
import com.lowagie.text.pdf.PdfWriter
import org.jfree.chart.axis.CategoryAxis
import org.jfree.chart.axis.CategoryLabelPositions
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.chart.plot.CategoryPlot
import org.jfree.chart.plot.PlotOrientation
import org.jfree.data.category.CategoryDataset
import org.jfree.ui.RefineryUtilities


class Wykres {
    void wykresDlaA(String url) {
        JFrame frame = new JFrame("Waluty")

        String urlString = url
        String xml = Pobieranie.getXmlFileAsString(urlString)
        XmlSeparator wykres = new XmlSeparator()
        CategoryDataset dataset = wykres.createDatasetA(xml)
        JFreeChart chart = createChartA(dataset)
        ChartPanel chartPanel = new ChartPanel(chart)
        frame.setSize(400, 300)
        frame.setContentPane(chartPanel)
        RefineryUtilities.centerFrameOnScreen(frame)
        frame.setVisible(true)
    }
    private JFreeChart createChartA(final CategoryDataset dataset) {

        final JFreeChart chart = ChartFactory.createBarChart3D(
                "Wykres walut z tabeli A",         // chart title
                "Data",               // domain axis label
                "Wartość",                  // range axis label
                dataset,                  // data
                PlotOrientation.VERTICAL, // orientation
                true,                     // include legend
                true,                     // tooltips?
                false                     // URLs?
        );
        final CategoryPlot plot = chart.getCategoryPlot()
        final CategoryAxis domainAxis = plot.getDomainAxis()
        domainAxis.setCategoryLabelPositions(
                CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 5.0)
        );
        return chart

    }

   void pdf(String url) {

       String urlString = url
       String xml = Pobieranie.getXmlFileAsString(urlString)
       XmlSeparator wykres = new XmlSeparator()
       final CategoryDataset dataset = wykres.createDataset(xml)
       convertToPdf(createChart(dataset), 600, 400, "wykres.pdf")

    }


    void convertToPdf(JFreeChart chart, int width, int height, String filename) {

        Document document = new Document(new Rectangle(width, height))
        try {
            PdfWriter writer
            writer = PdfWriter.getInstance(document, new FileOutputStream(filename))
            document.open()
            PdfContentByte cb = writer.getDirectContent()
            PdfTemplate tp = cb.createTemplate(width, height)
            Graphics2D g2d = tp.createGraphics(width, height, new DefaultFontMapper())
            Rectangle2D r2d = new Rectangle2D.Double(0, 0, width, height)
            chart.draw(g2d, r2d)
            g2d.dispose()
            cb.addTemplate(tp, 0, 0)
        }
        catch (DocumentException de) {
            de.printStackTrace()
        }
        catch (FileNotFoundException e) {
            e.printStackTrace()
        }
        document.close()
    }
}
