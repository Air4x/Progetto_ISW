VPATH = VisualParadigm:Documentation

main.pdf: main.tex images
	lualatex $<

images: diagramma_uso.png diagramma_classi.png sequence prg er test

sequence: diagramma_sequenza_sottomissione.png diagramma_sequenza_conferenza.png diagramma_sequenza_revisori.png diagramma_sequenza_accesso.png diagramma_sequenza_registrazione.png diagramma_sequenza_conferenza_V2.png

prg: diagramma_prg_classi.png diagramma_prg_database.png diagramma_prg_entity.png diagramma_prg_controller.png diagramma_prg_dto.png diagramma_prg_boundary.png diagramma_prg_utility.png diagramma_prg_sequenza_registrazione.png diagramma_prg_sequenza_accesso.png diagramma_prg_sequenza_conferenza.png

er: er_finale.png

test: test_createConference.png test_submitArticle.png test_nearDeadline.png test_saveUser.png

diagramma_classi.png: diagramma_classi.puml
	java -DPLANTUML_LIMIT_SIZE=16384 -jar plantuml.jar $^

diagramma_uso.png: diagramma_uso.puml
	java -DPLANTUML_LIMIT_SIZE=16384 -jar plantuml.jar $^

diagramma_sequenza_accesso.png: diagramma_sequenza_accesso.puml
	java -DPLANTUML_LIMIT_SIZE=16384 -jar plantuml.jar $^

diagramma_sequenza_registrazione.png: diagramma_sequenza_registrazione.puml
	java -DPLANTUML_LIMIT_SIZE=16384 -jar plantuml.jar $^

diagramma_sequenza_sottomissione.png: diagramma_sequenza_sottomissione.puml
	java -DPLANTUML_LIMIT_SIZE=16384 -jar plantuml.jar $^

diagramma_sequenza_conferenza.png: diagramma_sequenza_conferenza.puml
	java -DPLANTUML_LIMIT_SIZE=16384 -jar plantuml.jar $^

diagramma_sequenza_revisori.png: diagramma_sequenza_revisori.puml
	java -DPLANTUML_LIMIT_SIZE=16384 -jar plantuml.jar $^

diagramma_prg_sequenza_registrazione.png: diagramma_prg_sequenza_registrazione.puml
	java -DPLANTUML_LIMIT_SIZE=16384 -jar plantuml.jar $^

diagramma_prg_sequenza_accesso.png: diagramma_prg_sequenza_accesso.puml
	java -DPLANTUML_LIMIT_SIZE=16384 -jar plantuml.jar $^

diagramma_prg_sequenza_conferenza.png: diagramma_prg_sequenza_conferenza.puml
	java -DPLANTUML_LIMIT_SIZE=16384 -jar plantuml.jar $^

diagramma_prg_classi.png: diagramma_prg_classi.puml
	java -DPLANTUML_LIMIT_SIZE=16384 -jar plantuml.jar $^

diagramma_prg_database.png: diagramma_prg_database.puml
	java -DPLANTUML_LIMIT_SIZE=16384 -jar plantuml.jar $^

diagramma_prg_entity.png: diagramma_prg_entity.puml
	java -DPLANTUML_LIMIT_SIZE=16384 -jar plantuml.jar $^

diagramma_prg_controller.png: diagramma_prg_controller.puml
	java -DPLANTUML_LIMIT_SIZE=16384 -jar plantuml.jar $^

diagramma_prg_boundary.png: diagramma_prg_boundary.puml
	java -DPLANTUML_LIMIT_SIZE=16384 -jar plantuml.jar $^

er_finale.png: er_finale.pum
	java -DPLANTUML_LIMIT_SIZE=16384 -jar plantuml.jar $^

diagramma_prg_dto.png: diagramma_prg_dto.puml
	java -DPLANTUML_LIMIT_SIZE=16384 -jar plantuml.jar $^

diagramma_prg_utility.png: diagramma_prg_utility.pum
	java -DPLANTUML_LIMIT_SIZE=16384 -jar plantuml.jar $^

test_submitArticle.png: test_submitArticle.puml
		java -DPLANTUML_LIMIT_SIZE=16384 -jar plantuml.jar $^

test_createConference.png: test_createConference.puml
		java -DPLANTUML_LIMIT_SIZE=16384 -jar plantuml.jar $^

test_nearDeadline.png: test_nearDeadline.puml
		java -DPLANTUML_LIMIT_SIZE=16384 -jar plantuml.jar $^

diagramma_sequenza_conferenza_V2.png: diagramma_sequenza_conferenza_V2.puml
		java -DPLANTUML_LIMIT_SIZE=16384 -jar plantuml.jar $^

test_saveUser.png: test_saveUser.puml
		java -DPLANTUML_LIMIT_SIZE=16384 -jar plantuml.jar $^

clean:
	rm main.*



