(ns day1.part1
  (:require [clojure.java.io :as io]))

; Devuele de forma lazy todos los numeros de una linea
(defn numbers [line]
  (re-seq #"\d" line))

; Devuelve, como entero, el primer y el ultimo digito de la linea
(defn calibration-values [line]
  (let [nums (numbers line)
        n1 (first nums)
        n2 (last nums)]

    (Integer. (str n1 n2))))

; Abre el archivo dado, calcula los valores de calibracion de cada linea
; y los suma utilizando con reduce
(with-open [rdr (io/reader "day1/input.txt")]
  (reduce + (map calibration-values (line-seq rdr))))

