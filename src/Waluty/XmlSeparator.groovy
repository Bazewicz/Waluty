package Waluty
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

}