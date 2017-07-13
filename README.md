# SoccerDataExtractor


1. Java Version

The program was coded in Java SE Runtime Environment 8 Update 131 (JRE 8u131).

2. Project Download

Soccer Data Extractor is available as a Java Archive (JAR) file at

3. Program Overview

The program is composed of one main class (JAR launch configuration) to open the GUI, 4 agent classes (one for each data retrieval assignment), and a converter class to transform the datasets into a Comma-Separated Values (CSV) file.

Upon a button click, an ActionListener event is fired, instructing the appropriate agent class to retrieve text data, under very specific parameters (in-between nodes, matching IDs, etc). The information is then parsed, cleaned, and then aggregated into List abstractions.

Finally, the aggregate data is processed through the converter file into a CSV, and returned to the GUI for display. The total elapsed run-time of the data extraction and display operations is also shown at the bottom. 

4. Library Dependencies

Data Extractor utilizes the Jaunt webscraping library to perform W3C/WHATWG-defined Document Object Model I.a (DOM) operations on HTML pages, in order to traverse tables and locate information data. 

Sample: Table retrieval code, using the Jaunt API UserAgent browser.

The overarching algorithm begins by instantiating a UserAgent, generally followed by Node retrieval, descendant Node searching, and text aggregation. I.b

Jaunt v1.3.1 II.a is pre-packaged into the program’s JAR file as an external reference library, and thus, no additional download is necessary on the part of the client.

5. External Resources

The program uses the IconArchive soccer icon image III.a, available with a freeware license, for the JFrame icon. The icon is visible next to the program title in both the frame window and the Windows taskbar. 

6. Mapping Specification

The first assignment (“All Clubs”) involves the aggregation of ‘StateCode’ data (CA, IN, etc), converted from the available abbreviations (Calif., Ind., etc). Thus, there was a need for a comprehensive mapping specification, which was implemented through a HashMap and primarily sourced from the State Abbreviations/Codes Table.  II.a

Sample: A few of the custom mappings, including the two Canadian provinces, 
as well as the two to address the Virginia abbreviation inconsistency in the dataset.

However, due to inconsistencies in the assignment URL dataset (switching between “Va.” and “VA”., for example) and discrepancies between the dataset and the sourced abbreviations table, additional mappings were implemented. 

7. Additional Solutions

In addition to the Jaunt library-based project, another dependency-free solution was tested on the first assignment, using a BufferedReader to read the webpage, positive/negative criteria to control the lines read, and a complicated regex pattern (<.*?> and \\s{2,}) to filter out the HTML tags. 

8. Project References

General
DOM Navigation - jsoup.org/cookbook/extracting-data/dom-navigation
Web Scraping - stackoverflow.com/questions/3202305/web-scraping-with-java
State Abbreviations/Codes Table - infoplease.com/state-abbreviations-and-state-postal-codes 
Library
Jaunt Webscraping v1.3.1 JAR (Limited License) - http://jaunt-api.com 
Resource
IconArchive Soccer Icon PNG - iconarchive.com/icons/martin-berube/sport/128/Soccer-icon.png
