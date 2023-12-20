(ns day3.part1
  (:require
   [clojure.java.io :as io]
   [clojure.string :as string]
   [clojure.set :refer [union]]))

(def sample "467..114..
...*......
..35..633.
......#...
617*......
.....+.58.
..592.....
......755.
...$.*....
.664.598..")

(defn symbols [input]
  (for [[i line] (map-indexed vector (string/split-lines input))
        [j ch] (map-indexed vector (string/split line #""))
        :when (re-matches #"[^\d\.]" ch)]
    [i j]))

(defn expand-symbol [position]
  (for [i (range -1 2)
        j (range -1 2)]
    (map + [i j] position)))

(defn expand-symbols [symbols]
  (reduce #(union %1 (set (expand-symbol %2))) #{} symbols))

(defn re-seq-pos [pattern string]
  (let [matcher (re-matcher pattern string)]
    (loop [matches []]
      (if (.find matcher)
        (recur (conj matches
                     {:start (.start matcher)
                      :end (.end matcher)
                      :group (.group matcher)}))
        matches))))

(defn solve [input]
  (let [valid (expand-symbols (symbols input))]
    (reduce + (for [[i line] (map-indexed vector (string/split-lines input))
                    number (re-seq-pos #"\d+" line)
                    :when (some #(contains? valid [i %1]) (range (:start number) (:end number)))]
                (Integer. (:group number))))))

(solve sample)

(solve (slurp "day3/input.txt"))
