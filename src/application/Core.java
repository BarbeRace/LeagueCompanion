package application;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Core {

    public static final ArrayList<String> positions = new ArrayList<String>() {
        {
            add("top");
            add("jungle");
            add("middle");
            add("bottom");
            add("support");
        }
    };

    /**
     * Effectue la requête get et retourne la réponse dans un string
     */
    public static String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }

    /**
     * Corrige certains noms pourraves de compétences
     */
    public static ArrayList<String> correctPerksNames(ArrayList<String> input) {
        int length = input.size();
        for (int t = 0; t < length; t++) {
            if (input.get(t).equals("Legend: Alacrity")) {
                input.set(t, "Alacrity");
            } else if (input.get(t).equals("Legend: Tenacity")) {
                input.set(t, "Tenacity");
            } else if (input.get(t).equals("Legend: Bloodline")) {
                input.set(t, "Bloodline");
            }
        }
        return input;
    }

    /**
     * Méthode getChamps : permet de récupérer les champions dans une liste de String
     */
    public static ArrayList<String> getChamps() {
        ArrayList<String> champs = new ArrayList<String>();
        String url = "https://lol.gamepedia.com/Portal:Champions/List";
        String reponse = null;
        try {
            reponse = getHTML(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Document doc = Jsoup.parse(reponse);
        Elements noms = doc.select("a").nextAll();
        for (Element e : noms) {
            if (e.text().length() > 2 && e.text().length() < 15) {
                champs.add(e.text());
            }
        }
        // On supprime le champ "search" trouvé
        champs.remove(0);
        // Correction de noms de champions complexes
        Pattern p = Pattern.compile("[^A-Za-z0-9]");
        for (String str : champs) {
            Matcher m = p.matcher(str);
            if (m.find()) {
                champs.set(
                        champs.indexOf(str), str.replaceAll("[^a-zA-Z0-9]", "")
                );
            }
        }
        return champs;
    }

    /**
     * Récupère le détail d'un champion à partir de son nom
     */
    public static DetailsDto getInfo(String champName) throws Exception {
        String stats = null;
        ArrayList<String> perks = new ArrayList<String>();
        ArrayList<String> fragments = new ArrayList<String>();
        String position = "";
        DetailsDto retour = new DetailsDto();
        try {
            stats = getHTML("http://www.op.gg/champion/" + champName + "/statistics");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Document doc = Jsoup.parse(stats);
        Elements imgtags = doc.select("img");
        Elements titleTag = doc.select("title");
        for (Element e : imgtags) {
            if (!e.attr("src").contains("e_grayscale") && e.attr("src").contains("perk")) {
                if (!e.attr("alt").contains("Learn more") && e.attr("alt").length() > 2) {
                    if (e.attr("src").contains("5001")) {
                        fragments.add("5001");
                    } else if (e.attr("src").contains("5002")) {
                        fragments.add("5002");
                    } else if (e.attr("src").contains("5003")) {
                        fragments.add("5003");
                    } else if (e.attr("src").contains("5005")) {
                        fragments.add("5005");
                    } else if (e.attr("src").contains("5007")) {
                        fragments.add("5007");
                    } else if (e.attr("src").contains("5008")) {
                        fragments.add("5008");
                    } else {
                        perks.add(e.attr("alt"));
                    }
                }
            }
        }
        if(titleTag.size() == 1) {
            try {
                String tmp = titleTag.get(0).toString().replaceAll("[^a-zA-Z0-9]", "");
                for(String pos : positions) {
                    if(tmp.toLowerCase().contains(pos)) { position = pos; }
                }
            } catch (Exception ex) {
                System.out.println("Problème récupération de la position du champion");
                System.out.println(titleTag.get(0).toString());
            }
        }
        if(perks == null || perks.isEmpty()) {
            throw new Exception("Une erreur est survenue lors du chargement des information du champion " + champName);
        }
        retour.setPerks(correctPerksNames(perks));
        retour.setFragments(fragments);
        retour.setPosition(position);
        return retour;
    }
}
