VPATH = VisualParadigm:Documentation

main.pdf:  main.tex images
	lualatex $<

images: diagramma_uso.png diagramma_classi.png sequence prg er test

sequence: diagramma_sequenza_sottomissione.png diagramma_sequenza_conferenza.png diagramma_sequenza_revisori.png diagramma_sequenza_accesso.png diagramma_sequenza_registrazione.png

prg: diagramma_prg_classi.png diagramma_prg_database.png diagramma_prg_entity.png diagramma_prg_controller.png diagramma_prg_dto.png

er: er_finale.png

test: test_createConference.png test_submitArticle.png test_nearDeadline.png test_saveUser.png

diagramma_classi.png: diagramma_classi.puml
	java -jar plantuml.jar $^

diagramma_uso.png: diagramma_uso.puml
	java -jar plantuml.jar $^

diagramma_sequenza_accesso.png: diagramma_sequenza_accesso.puml
	java -jar plantuml.jar $^

diagramma_sequenza_registrazione.png: diagramma_sequenza_registrazione.puml
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

diagramma_prg_controller.png: diagramma_prg_controller.puml
	java -jar plantuml.jar $^

er_finale.png: er_finale.pum
	java -jar plantuml.jar $^

diagramma_prg_dto.png: diagramma_prg_dto.puml
	java -jar plantuml.jar $^
	
test_submitArticle.png: test_submitArticle.puml
		java -jar plantuml.jar $^

test_createConference.png: test_createConference.puml
		java -jar plantuml.jar $^

test_nearDeadline.png: test_nearDeadline.puml
		java -jar plantuml.jar $^

test_saveUser.png: test_saveUser.puml
		java -jar plantuml.jar $^

clean:
	rm main.*



