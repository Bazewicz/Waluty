package Waluty

import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.chart.plot.PlotOrientation
import org.jfree.data.category.CategoryDataset
import org.jfree.ui.ApplicationFrame

import java.awt.Dimension

class Wykres extends ApplicationFrame{
   public Wykres(String title, String url) {
        super(title);
        String urlString = url
        String xml = Pobieranie.getXmlFileAsString(urlString)
        XmlSeparator wykres=new XmlSeparator()
        final CategoryDataset dataset = wykres.createDataset(xml);
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);
    }
    private JFreeChart createChart(final CategoryDataset dataset) {

        final JFreeChart chart = ChartFactory.createBarChart(
                "Wykres walut",         // chart title
                "Waluty",               // domain axis label
                "Wartość",                  // range axis label
                dataset,                  // data
                PlotOrientation.VERTICAL, // orientation
                true,                     // include legend
                true,                     // tooltips?
                false                     // URLs?
        );


        return chart;

    }
}
