package fileSearcher

import org.scalatest.FlatSpec
import java.io.File

class FilterCheckerTests extends FlatSpec{
  "FilterChecker passed a list where one file matches the filter" should
  "return a list with that file" in {
    val matchingFile = FileObject(new File("match"))
    val listOfFiles = List(FileObject(new File("random")), matchingFile)
    val matchedFiles = FilterChecker("match") findMatchedFiles listOfFiles
    assert(matchedFiles == List(matchingFile))
  }
  
  "FilterChecker passed a list with a directory that matches the filter" should 
  "should not return the directory" in {
    val listofIOObjects = List(FileObject(new File("random")), DirectoryObject(new File("match")) )
    val matchedFiles = FilterChecker("match") findMatchedFiles listofIOObjects
    assert(matchedFiles.length == 0)
  }
  
  "FilterChecker passed a file with content that matches the filter 3 times" should
  "return a 3" in {
    val isContentMatched = FilterChecker("pluralsight")
                           .findMatchedContentCount(new File("." + File.separator + "testfiles" + File.separator + "pluralsight.data"))
    assert(isContentMatched == 3)                       
  }
  
  "FilterChecker passed a file with content that does not match the filter" should
  "return a 0" in {
    val isContentMatched = FilterChecker("pluralsight")
                           .findMatchedContentCount(new File("." + File.separator + "testfiles" + File.separator +"testFile.txt"))
    assert(isContentMatched == 0)
  }
}