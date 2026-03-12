package util;

// Formaterer flaskestrenge til læsbart output.
public class BottleFormatter {

    // Konverterer f.eks. "øl16" til "øl #16" og "vand16" til "vand #16"
    public static String format(String bottle) {
        if (bottle.startsWith("øl")) {
            return "øl #" + bottle.substring(2);
        }
        return "vand #" + bottle.substring(4);
    }
}