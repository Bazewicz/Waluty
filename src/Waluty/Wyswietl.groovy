package Waluty

class Wyswietl {
    String wyswietl(String url){
        def builder = new StringBuilder()
        String urlString = url
        String xml = Pobieranie.getXmlFileAsString(urlString)

        List<Naglowek> tables = new ArrayList<>()
        XmlSeparator.parseXmlAndAddToList(xml, tables)

        for (ex in tables) {
            builder.append(ex)
            builder.append("\n")

        for (rx in ex.getRates()) {
            builder.append(rx)
            builder.append("\n")
        }

    }
        return builder.toString()
}
}

