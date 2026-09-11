(ns culture.facts
  "Country-level regional-culture catalog for Colombia (COL) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). Sibling namespace to
  `marketentry.facts` / `statute.facts` (ADR-2607141700); city-level
  counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"COL"
   [{:culture/id "col.dish.ajiaco"
     :culture/name "Ajiaco"
     :culture/country "COL"
     :culture/kind :dish
     :culture/summary "Soup common to Colombia, Cuba and Peru; especially popular in the Colombian capital Bogotá, where it is called ajiaco santafereño."
     :culture/url "https://en.wikipedia.org/wiki/Ajiaco"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "col.dish.bandeja-paisa"
     :culture/name "Bandeja paisa"
     :culture/country "COL"
     :culture/kind :dish
     :culture/summary "One of the most representative meals in Colombian cuisine, especially of the Antioquia department and the Paisa region."
     :culture/url "https://en.wikipedia.org/wiki/Bandeja_paisa"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "col.dish.arepa"
     :culture/name "Arepa"
     :culture/country "COL"
     :culture/kind :dish
     :culture/summary "Flatbread of ground maize dough eaten in northern South America since pre-Columbian times, notable primarily in the cuisine of Colombia and Venezuela."
     :culture/url "https://en.wikipedia.org/wiki/Arepa"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "col.beverage.aguapanela"
     :culture/name "Aguapanela"
     :culture/country "COL"
     :culture/kind :beverage
     :culture/summary "Drink made by dissolving panela in water, commonly found throughout Colombia, especially in the Andes region."
     :culture/url "https://en.wikipedia.org/wiki/Aguapanela"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "col.product.colombian-coffee"
     :culture/name "Colombian coffee"
     :culture/country "COL"
     :culture/kind :product
     :culture/summary "Colombian coffee production has a reputation for mild, well-balanced beans; the European Union granted Colombian coffee protected designation of origin status in 2007."
     :culture/url "https://en.wikipedia.org/wiki/Coffee_production_in_Colombia"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "col.craft.sombrero-vueltiao"
     :culture/name "Sombrero vueltiao"
     :culture/country "COL"
     :culture/kind :craft
     :culture/summary "Traditional hat from Colombia and one of its symbols, woven from caña flecha and originating with the Zenú people of the northern Caribbean region."
     :culture/url "https://en.wikipedia.org/wiki/Sombrero_vueltiao"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "col.festival.carnival-of-barranquilla"
     :culture/name "Carnival of Barranquilla"
     :culture/country "COL"
     :culture/kind :festival
     :culture/summary "One of Colombia's most important folkloric celebrations, held annually in Barranquilla; UNESCO declared it a Masterpiece of the Oral and Intangible Heritage of Humanity in 2003."
     :culture/url "https://en.wikipedia.org/wiki/Carnival_of_Barranquilla"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "col.heritage.coffee-cultural-landscape"
     :culture/name "Coffee Cultural Landscape"
     :culture/name-local "Eje Cafetero"
     :culture/country "COL"
     :culture/kind :heritage
     :culture/summary "Coffee-growing region of Colombia (Eje Cafetero) whose urban settlements were inscribed on the UNESCO World Heritage List in 2011 as the Coffee Cultural Landscape."
     :culture/url "https://en.wikipedia.org/wiki/Colombian_coffee_growing_axis"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-col culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "COL"))
                 " COL entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
