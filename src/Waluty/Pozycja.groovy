package Waluty

class Pozycja {
    private String country = null
    private String currency = null
    private String code = null
    private String mid = null
    private String bid = null
    private String ask = null

    void setCountry(String country) {
        this.country = country
    }

    void setCurrency(String currency) {
        this.currency = currency
    }

    void setCode(String code) {
        this.code = code
    }

    void setMid(String mid) {
        this.mid = mid
    }

    void setBid(String bid) {
        this.bid = bid
    }

    void setAsk(String ask) {
        this.ask = ask
    }

    @Override
    String toString() {
        StringBuilder builder = new StringBuilder()

        if (country) {
            builder.append("Państwo: ")
            builder.append(country).append("\n")
        }

        if (currency){
            builder.append("Waluta: ")
            builder.append(currency).append("\n")
        }

        if (code) {
            builder.append("Kod: ")
            builder.append(code).append("\n")
        }

        if (mid) {
            builder.append("Wartość: ")
            builder.append(mid).append(" zł\n")
        }
        if (bid) {
            builder.append("Sprzedaż: ")
            builder.append(bid).append(" zł\n")
        }
        if (ask) {
            builder.append("Kupno: ")
            builder.append(ask).append(" zł\n")
        }

        return builder.toString()
    }
}
