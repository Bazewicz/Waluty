package Waluty

class Pobieranie {
    static def getXmlFileAsString(String urlString) {
        def builder = new StringBuilder()
        def inputStream = null
        def reader
        def line
        def url

        try {
            url = new URL(urlString)
            inputStream = url.openStream()
            reader = new BufferedReader(new InputStreamReader(inputStream))

            while ((line = reader.readLine()) != null) {
                builder.append(line)
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace()
        } catch (IOException ioe) {
            ioe.printStackTrace()
        } finally {
            try {
                if (inputStream != null) inputStream.close()
            } catch (IOException ioe) {
                ioe.printStackTrace()
            }
        }

        return builder.toString()
    }
}