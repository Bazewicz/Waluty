package Waluty

import org.jfree.data.category.CategoryDataset
import org.jfree.data.category.DefaultCategoryDataset

class XmlSeparator {
    static def parseXmlAndAddToList(String xml, List<Naglowek> exchangeRatesTables) {
        def document = new XmlParser().parseText(xml)
        Naglowek table = null
        Pozycja rate = null
        document.ExchangeRatesTable.each {
            bk ->
                table = new Naglowek()

                table.setTable("${bk.Table.text()}")
                table.setNo("${bk.No.text()}")
                table.setEffectiveDate("${bk.EffectiveDate.text()}")
                bk.Rates.Rate.each {
                    bt ->
                        rate = new Pozycja()

                        rate.setCountry("${bt.Country.text()}")
                        rate.setCurrency("${bt.Currency.text()}")
                        rate.setCode("${bt.Code.text()}")
                        rate.setMid("${bt.Mid.text()}")
                        rate.setAsk("${bt.Ask.text()}")
                        rate.setBid("${bt.Bid.text()}")

                        table.getRates().add(rate)
                }
                exchangeRatesTables.add(table)

        }
    }
     CategoryDataset createDataset(String xml) {
         def document = new XmlParser().parseText(xml)
         Naglowek table = null
         Pozycja rate = null
         String []daty =new String[100]
         String []eur =new String[100]
         String []usd =new String[100]
         String []chf =new String[100]
         String []gbp =new String[100]
         double wartosceur
         double wartoscusd
         double wartoscchf
         double wartoscgbp
         int i=0
         int j=0
         document.ExchangeRatesTable.each {

             bk ->
                 table = new Naglowek()
                 daty[i]=bk.EffectiveDate.text()
                 bk.Rates.Rate.each {
                     bt ->
                         rate = new Pozycja()

                         table.getRates().add(rate)
                         if (bt.Code.text()=="EUR")
                             eur[i]=bt.Mid.text()
                         if (bt.Code.text()=="USD")
                             usd[i]=bt.Mid.text()
                         if (bt.Code.text()=="CHF")
                             chf[i]=bt.Mid.text()
                         if (bt.Code.text()=="GBP")
                             gbp[i]=bt.Mid.text()
                 }
                 i++
         }


         String series1 = "USD";
         String series2 = "EUR";
         String series3 = "CHF";
         String series4 = "GBP";
         DefaultCategoryDataset dataset = new DefaultCategoryDataset()
         for (j==0;j<=i;j++){
         if(daty[j]!=null) {
             wartosceur = Double.parseDouble(eur[j])
             wartoscchf = Double.parseDouble(chf[j])
             wartoscgbp = Double.parseDouble(gbp[j])
             wartoscusd = Double.parseDouble(usd[j])

             dataset.addValue(wartoscusd, series1, daty[j])
             dataset.addValue(wartosceur, series2, daty[j])
             dataset.addValue(wartoscchf, series3, daty[j])
             dataset.addValue(wartoscgbp, series4, daty[j])
            }
         }
        return dataset;

    }

}