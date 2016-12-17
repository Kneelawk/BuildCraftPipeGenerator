package org.kneelawk.buildcraftpipegenerator

import org.kneelawk.wavefronttools.ObjWriter
import java.io.File
import java.io.FileOutputStream

object BuildCraftPipeGenerator {
  def main(args: Array[String]) {
    val outputDir = new File("output")
    if (!outputDir.exists()) outputDir.mkdirs()
    
    val bigFile = new File(outputDir, "full.obj")
    
    val bigOut = new ObjWriter(new FileOutputStream(bigFile))
  }
}