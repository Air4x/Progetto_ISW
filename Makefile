VPATH = VisualParadigm:Documentation

main.pdf:  main.tex images
	lualatex $<

images: diagramma_uso.png diagramma_classi.png sequence prg er

sequence: diagramma_sequenza_sottomissione.png diagramma_sequenza_conferenza.png diagramma_sequenza_revisori.png

prg: diagramma_prg_classi.png diagramma_prg_database.png diagramma_prg_entity.png diagramma_prg_controller.png

er: er_finale.png

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

diagramma_prg_database.png: diagramma_prg_database.puml
	java -jar plantuml.jar $^

diagramma_prg_entity.png: diagramma_prg_entity.pum
	java -jar plantuml.jar $^

diagramma_prg_controller.png: diagramma_prg_controller.pum
	java -jar plantuml.jar $^

er_finale.png: er_finale.pum
	java -jar plantuml.jar $^

clean:
	rm main.*



