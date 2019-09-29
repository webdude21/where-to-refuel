package where.to.refuel.server.model.service;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.util.Map;
import java.util.stream.Collectors;

public class PetrolStationsByFuelTypeHtmlParser {

  public static Map<Integer, Double> parse(String html) {
    var fragment = Jsoup.parse(html);
    var selected = fragment.select("table tbody tr");
    return selected.stream()
      .map(element -> element.select("td"))
      .collect(Collectors.toMap(PetrolStationsByFuelTypeHtmlParser::getId, PetrolStationsByFuelTypeHtmlParser::getPrice));
  }

  private static double getPrice(Elements allElements) {
    var priceWithCurrency = allElements.get(4).text();
    var priceStripped = StringUtils.split(priceWithCurrency, " ")[0];
    var priceWithProperDelimiter = priceStripped.replace(',', '.');
    return Double.parseDouble(priceWithProperDelimiter);
  }

  private static int getId(Elements allElements) {
    var href = allElements.get(0).children().get(0).attr("href");
    var extractedId = StringUtils.removeEnd(StringUtils.remove(href, "/gasstation/id/"), "?lang=bg");
    return Integer.parseInt(extractedId);
  }
}
