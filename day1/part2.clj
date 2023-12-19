(ns day1.part2
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

; Define un hashmap con los numeros escritos y su valor numerico.
(def translation {"one" "1"
                  "two" "2"
                  "three" "3"
                  "four" "4"
                  "five" "5"
                  "six" "6"
                  "seven" "7"
                  "eight" "8"
                  "nine" "9"})

; Traduce un digito en texto a un digito en numero, o devuelve el propio valor
; en caso de no encontrar traduccion.
(defn translate [digit] (or (translation digit) digit))

; Define un patron que utiliza un positive lookahead para hayar un numero.
; Esto permite encontrar numeros con solapamiento.
(def digits-pattern
  (re-pattern (format "(?=(%s|%s))"
                      #"\d"
                      (string/join "|" (keys translation)))))

; Devuelve todos los numeros de una linea, convirtiendo los numeros 
; en formato texto a formato numero.
(defn numbers [line]
  (map
   (fn [[_, digit]] (translate digit))
   (re-seq digits-pattern line)))

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

