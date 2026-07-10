(ns marketentry.facts "Colombia market-entry catalog.")
(def catalog
  {"COL" {:name "Colombia"
          :owner-authority "Colombia Compra Eficiente / SECOP II"
          :legal-basis "Ley 80/1993; Decreto 1082/2015"
          :national-spec "SECOP II supplier registration + NIT"
          :provenance "https://www.colombiacompra.gov.co/"
          :required-evidence ["NIT record" "SECOP II registration record" "RUES extract" "Authorized-representative record"]
          :rep-owner-authority "contracting authorities / CCE"
          :rep-legal-basis "Colombian legal entity (NIT) typically required for SECOP awards"
          :rep-provenance "https://www.colombiacompra.gov.co/"
          :corporate-number-owner-authority "DIAN / RUES"
          :corporate-number-legal-basis "NIT"
          :corporate-number-provenance "https://www.dian.gov.co/"}
   "USA" {:name "United States" :owner-authority "GSA/SAM.gov" :legal-basis "FAR" :national-spec "SAM.gov" :provenance "https://sam.gov/"
          :required-evidence ["EIN record" "SAM.gov registration record" "State business registration record" "SAM UEI verification record"]}
   "BRA" {:name "Brazil" :owner-authority "Compras.gov.br" :legal-basis "Lei 14.133/2021" :national-spec "Compras.gov.br" :provenance "https://www.gov.br/compras/"
          :required-evidence ["CNPJ record" "Compras.gov.br registration" "SICAF record" "Authorized-representative record"]}
   "MEX" {:name "Mexico" :owner-authority "CompraNet" :legal-basis "LAASSP" :national-spec "CompraNet" :provenance "https://compranet.hacienda.gob.mx/"
          :required-evidence ["RFC record" "CompraNet registration" "RFC extract" "Authorized-representative record"]}})

(defn spec-basis [iso3] (get catalog iso3))
(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s) missing (remove catalog iso3s)]
     {:requested (count iso3s) :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note "R0 catalog seed"})))
(defn required-evidence-satisfied? [iso3 submitted]
  (when-let [{:keys [required-evidence]} (spec-basis iso3)]
    (= (count required-evidence) (count (filter (set submitted) required-evidence)))))
(defn evidence-checklist [iso3] (:required-evidence (spec-basis iso3) []))
(defn rep-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:rep-owner-authority sb)
      (select-keys sb [:rep-owner-authority :rep-legal-basis :rep-provenance]))))
(defn corporate-number-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:corporate-number-owner-authority sb)
      (select-keys sb [:corporate-number-owner-authority :corporate-number-legal-basis :corporate-number-provenance]))))
