VPATH = VisualParadigm:Documentation

main.pdf:  main.tex images
	lualatex $<

images: diagramma_uso.png

diagramma_uso.png: diagramma_uso.puml
	java -jar plantuml.jar $^

