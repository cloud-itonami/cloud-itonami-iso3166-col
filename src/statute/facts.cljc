(ns statute.facts
  "General-law compliance catalog for Colombia (COL) -- extends this
  repo's existing `marketentry.facts` (public-procurement market-entry
  only, narrow scope) with a second, orthogonal catalog of statutes a
  company generally must track for compliance. Mirrors
  cloud-itonami-iso3166-jpn/-usa/-esp/-swe/-nor/-dnk/-fin/-prt/-bel/-bra/-mex/-chl/-arg/-zaf's
  `statute.facts` (ADR-2607141700, cloud-itonami-compliance-fact-federation).

  secretariasenado.gov.co (the usual first-choice Colombian legislative
  portal, and the one used successfully by other Colombian-sourced
  documents elsewhere) was entirely unreachable this tick (connect
  ECONNREFUSED 200.7.106.227:443 at the TCP level, not an HTTP error --
  the same failure class hit for Mexico's diputados.gob.mx in an
  earlier tick). Every entry here instead cites
  funcionpublica.gov.co's official 'Gestor Normativo' (Departamento
  Administrativo de la Función Pública, a Colombian government agency)
  -- never fabricated. A law not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of statute entries."
  {"COL"
   [{:statute/id "col.codigo-de-comercio-decreto-410-1971"
     :statute/title "Código de Comercio (Decreto 410 de 1971)"
     :statute/jurisdiction "COL"
     :statute/kind :law
     :statute/law-number "Decreto 410 de 1971"
     :statute/url "https://www.funcionpublica.gov.co/eva/gestornormativo/norma.php?i=41102"
     :statute/url-provenance :official-funcion-publica-gov-co
     :statute/enacted-date "1971-03-27"
     :statute/retrieved-at "2026-07-15"
     :statute/topic #{:corporate-governance :incorporation}}
    {:statute/id "col.ley-1581-2012-proteccion-datos"
     :statute/title "Ley Estatutaria 1581 de 2012 (Protección de Datos Personales)"
     :statute/jurisdiction "COL"
     :statute/kind :law
     :statute/law-number "Ley 1581 de 2012"
     :statute/url "https://www.funcionpublica.gov.co/eva/gestornormativo/norma.php?i=49981"
     :statute/url-provenance :official-funcion-publica-gov-co
     :statute/enacted-date "2012-10-17"
     :statute/retrieved-at "2026-07-15"
     :statute/topic #{:data-protection :privacy}}
    {:statute/id "col.codigo-sustantivo-trabajo-decreto-2663-1950"
     :statute/title "Código Sustantivo del Trabajo (Decreto 2663 de 1950)"
     :statute/jurisdiction "COL"
     :statute/kind :law
     :statute/law-number "Decreto 2663 de 1950"
     :statute/url "https://www.funcionpublica.gov.co/eva/gestornormativo/norma.php?i=199983"
     :statute/url-provenance :official-funcion-publica-gov-co
     :statute/enacted-date "1950-08-05"
     :statute/retrieved-at "2026-07-15"
     :statute/topic #{:labor :employment}}]})

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
      :note (str "cloud-itonami-iso3166-col statute.facts Wave 0 (ADR-2607141700): "
                 (count (get catalog "COL")) " COL statutes seeded with an "
                 "official funcionpublica.gov.co citation. Extend "
                 "`statute.facts/catalog`, never fabricate a law-id or URL.")})))

(defn by-topic [iso3 topic]
  (filterv #(contains? (:statute/topic %) topic) (spec-basis iso3)))
