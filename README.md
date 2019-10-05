# Language-recognition
An object oriented Java program for language based recognition on the analysis of frequencies. The program is able to read entire books in different languages to better learn each language. Javadoc included.
## Notes and Instructions
The program, if launched, is able to recognize only texts writen in English and Italian.
In case you would like to train the program with other languages, you have to download new text fles, save
them in the texts directory, and use the appropriate methods.

The program can not recognize leters that are not in the english alphabet. For this reason it could
encounter difficulties with languages like German.

In case the program makes inaccuracies, you can train it with new books writen in the known languages.

The <b> Histogram </b> class is used only to make it easier visualize a linguistic model. It is a class used mainly for
testing. The program works indepently from it. For these reasons the print method of the Trainer class prints on stdout.
