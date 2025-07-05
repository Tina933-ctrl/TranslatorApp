
**TranslatorApp** is a Java-based application for translating text between multiple languages using XML, JAXB, and a Swing-based client interface.


## 🌍 Supported Languages

- English
- Italian
- Romanian
- French
- Spanish
- German

 Features

- Translate words and phrases between English, Italian, Romanian, French, Spanish, and German
- Load dictionary data dynamically from XML using **JAXB**
- Client-side GUI built with **Java Swing**
- Clean separation of source and target languages
- Client-server communication via HTTP using TomEE
- Swing interface for a user-friendly experience
- Flexible language mapping via source (`src`) and target (`tgt`) attributes in XML
- Easily extendable to support more languages

- Translate words and phrases between English, Italian, Romanian, French, Spanish, and German
- 📘 Load dictionary data dynamically from XML using **JAXB**
- 💻 Client-side GUI built with **Java Swing**
- Clean separation of source and target languages
- Client-server communication via HTTP using TomEE
- Swing interface for a user-friendly experience
- 🗂️ Flexible language mapping via source (`src`) and target (`tgt`) attributes in XML
- 🛠️ Easily extendable to support more languages

## 🛠 Technologies Used

- Java 17
- JAXB (Java Architecture for XML Binding)
- TomEE (Java EE Web Server)
- HTTP Servlets
- XML + XSD validation
- Swing (Java GUI)
- XML/XSD
- Maven
- Apache TomEE (server-side) 

## 🧩 Architecture

- **Model**: JAXB-mapped Dictionary objects
- **View**: Swing interface for user input/output
- **Controller**: Java Servlets deployed on TomEE
- **Data**: XML file with multilingual word entries

 How to Run


1. Import the Maven project in IntelliJ IDEA
2. Deploy the generated .war to a TomEE server.
3. Run the Swing client (`MainFrame.java`) from the desktop module
4. Ensure the XML dictionary is properly located and XSD-validated
5. The REST API will be available at: http://localhost:8080/translator-server/api/translate

XML Files   
dictionary.xml contains the words and their translations.
dictionary.xsd validates the XML structure used in the application.

 📁 Folder Structure
TranslatorApp/
├── translator-client/ # Swing-based desktop interface
│ └── TranslatorSwingClient.java
├── translator-server/ # TomEE servlet backend
│ ├── RestApplication.java
│ ├── TranslationResource.java
│ ├── DictionaryLoader.java
│ ├── Dictionary.java
│ ├── ObjectFactory.java
│ └── package-info.java
├── xml/ # Dictionary and schema
│ ├── dictionary.xml
│ └── dictionary.xsd
├── index.html # (Optional) HTML start page or documentation
├── pom.xml # Maven build file
└── README.md # This file

Example XML Entry:

<dictionary>
  <entry>
    <english>hello</english>
    <italian>ciao</italian>
    <romanian>salut</romanian>
    <french>bonjour</french>
    <spanish>hola</spanish>
    <german>hallo</german>
  </entry>
</dictionary>


License

This project is licensed under the MIT License.
