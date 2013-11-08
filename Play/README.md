Play
=====

Sandbox for toying with the Scala Play Framework

To setup play on Windows with Eclipse:

1. Install the Oracle JDK
2. Install Eclipse
3. Install the Scala IDE Eclipse plugin
4. Install the Scala Play 2.0 Eclipse Plugin (from the same update site as Scala IDE plugin, under incubator).
5. Download Scala Play
6. Add Scala Play folder to the path, so that you can run play.bat from the command line
7. Open command prompt, and go to the folder you want to create a Scala Play project in
8. Run play.bat new MyProjectName to create the play project
9. CD into the MyProjectName folder
10. Run the command "play.bat" to bring up the play console 
11. In the play console, run "eclipse with-source=true"
12. Open Eclipse
13. Import the Folder MyProjectName into eclipse
14. in the play console, run "~run" to run the MyProjectName (run with auto-compile)
15. Open web browser http://localhost:9000 (Scala Play Documentation should appear).
16. Make a change to one of the files in Eclipse (e.g. change the text "Your new application is ready." in app->controllers->Application.scala)
17. Refresh browser window