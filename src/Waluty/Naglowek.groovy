package Waluty

class Naglowek {
    private String table = null
    private String no = null
    private String effectiveDate = null
    private List<Pozycja> rates = new ArrayList<>()

    void setTable(String table) {
        this.table = table
    }

    void setNo(String no) {
        this.no = no
    }

    void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate
    }

    List<Pozycja> getRates() {
        return rates
    }

    @Override
    String toString() {
        return "Tabela '" + table + "' Walut dla dnia: '"+ effectiveDate + "'."
    }
}