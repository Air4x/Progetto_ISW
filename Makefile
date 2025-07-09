VPATH = VisualParadigm:Documentation

main.pdf:  main.tex images
	lualatex $<

images: diagramma_uso.png diagramma_classi.png sequence

sequence: diagramma_sequenza_sottomissione.png diagramma_sequenza_conferenza.png diagramma_sequenza_revisori.png

prg: diagramma_prg_classi.png

diagramma_classi.png: diagramma_classi.puml
	java -jar plantuml.jar $^

diagramma_uso.png: diagramma_uso.puml
	java -jar plantuml.jar $^

diagramma_sequenza_sottomissione.png: diagramma_sequenza_sottomissione.puml
	java -jar plantuml.jar $^

diagramma_sequenza_conferenza.png: diagramma_sequenza_conferenza.puml
	java -jar plantuml.jar $^

diagramma_sequenza_revisori.png: diagramma_sequenza_revisori.puml
	java -jar plantuml.jar $^

diagramma_prg_classi.png: diagramma_prg_classi.puml
	java -jar plantuml.jar $^
