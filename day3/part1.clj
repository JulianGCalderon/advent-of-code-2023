(ns day3.part1
  (:require
   [clojure.java.io :as io]))

(defn neighbours [position]
  (set (for [i (range -1 2)
             j (range -1 2)]
         (map + [i j] position))))

(defn string-find-all [pattern string]
  (let [matcher (re-matcher pattern string)]
    (for [_ (range)
          :while (.find matcher)]
      {:start (.start matcher)
       :end (.end matcher)
       :group (.group matcher)})))

(defn input-find-all [pattern input]
  (->> input
       (map-indexed (fn [row line]
                      (mapv #(assoc %1 :row row)
                            (string-find-all pattern line))))
       (flatten)))

(defn engine-part? [{:keys [start end row _]} symbols]
  (let [indices (for [x (range start end)] [row x])
        coords  (set (mapcat neighbours indices))]
    (some
     #(contains? coords %1)
     (map #(vector (:row %1) (:start %1)) symbols))))

(defn solve [input]
  (let [symbols (input-find-all #"[^\d\.]" input)]
    (->> input
         (input-find-all #"\d+")
         (filter #(engine-part? % symbols))
         (map :group)
         (map #(Integer/parseInt %))
         (reduce +))))

(with-open [rdr (io/reader "day3/input.txt")]
  (solve (line-seq rdr)))
