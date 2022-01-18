import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import org.apache.commons.io.FileUtils
import groovy.io.FileType

Path projectPath = Paths.get(request.outputDirectory, request.artifactId)
Path configPath = projectPath.resolve("no")		
File folder = configPath.toFile()
FileUtils.deleteDirectory(folder)
	
/* Check for lowering case on package path */
projectPath.toFile().eachDirRecurse { File dir ->
    println " ** Processing path ${dir.absolutePath}"
    newDirName = dir.name
    if(newDirName != "META-INF"){
        newDirName = dir.name.toLowerCase()
    }
    File newDir = new File(dir.parent + File.separator + newDirName)
    dir.renameTo(newDir)

    dir.eachFileRecurse(FileType.FILES) { file ->
        line = file.readLines().get(0)
        if(line.contains("__REMOVE_ME__")){
            println "   |-> Deleting unused file ${file.name}"
            file.delete()
        }
    }
}

// RENAME ROOT FOLDER INCLUDING CODE AP IF PRESENT
Files.move(projectPath, projectPath.resolveSibling(codeAP.toLowerCase() + "-" + projectPath.toFile().getName()))