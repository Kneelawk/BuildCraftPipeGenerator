package org.kneelawk.buildcraftpipegenerator

import java.io.File
import java.io.FileOutputStream

import org.kneelawk.wavefronttools.BasicFace
import org.kneelawk.wavefronttools.BasicMesh
import org.kneelawk.wavefronttools.ObjWriter
import org.kneelawk.wavefronttools.Vec2d
import org.kneelawk.wavefronttools.Vec3d

object BuildCraftPipeGenerator {
  def main(args: Array[String]) {
    val outputDir = new File("output")
    if (!outputDir.exists()) outputDir.mkdirs()

    val bigFile = new File(outputDir, "full.obj")

    val bigOut = new ObjWriter(new FileOutputStream(bigFile))

    for (i <- 0 until 0x40) {
      var name = "pipe"

      if (is(i, 0x01)) name += "X+" else name += "__"
      if (is(i, 0x02)) name += "X-" else name += "__"
      if (is(i, 0x04)) name += "Y+" else name += "__"
      if (is(i, 0x08)) name += "Y-" else name += "__"
      if (is(i, 0x10)) name += "Z+" else name += "__"
      if (is(i, 0x20)) name += "Z-" else name += "__"

      val pipe = new BasicMesh(name)
      pipe.addVertex(Vec3d(04d / 16d, 04d / 16d, 04d / 16d)) //
      pipe.addVertex(Vec3d(-4d / 16d, 04d / 16d, 04d / 16d)) //   2-------1
      pipe.addVertex(Vec3d(04d / 16d, -4d / 16d, 04d / 16d)) //  /|      /|
      pipe.addVertex(Vec3d(-4d / 16d, -4d / 16d, 04d / 16d)) // 4-------3 | z+
      pipe.addVertex(Vec3d(04d / 16d, 04d / 16d, -4d / 16d)) // | |     | | |
      pipe.addVertex(Vec3d(-4d / 16d, 04d / 16d, -4d / 16d)) // | 6-----|-5 | y+
      pipe.addVertex(Vec3d(04d / 16d, -4d / 16d, -4d / 16d)) // |/      |/  |/
      pipe.addVertex(Vec3d(-4d / 16d, -4d / 16d, -4d / 16d)) // 8-------7   0-----x+

      pipe.addTexCoord(Vec2d(04d / 16d, 00d / 16d)) //      01-------02
      pipe.addTexCoord(Vec2d(12d / 16d, 00d / 16d)) //       |        |
      pipe.addTexCoord(Vec2d(00d / 16d, 04d / 16d)) //       |        |
      pipe.addTexCoord(Vec2d(04d / 16d, 04d / 16d)) // 03---04-------05---06
      pipe.addTexCoord(Vec2d(12d / 16d, 04d / 16d)) //  |    |        |    |
      pipe.addTexCoord(Vec2d(16d / 16d, 04d / 16d)) //  |    |        |    |
      pipe.addTexCoord(Vec2d(00d / 16d, 12d / 16d)) //  |    |        |    |
      pipe.addTexCoord(Vec2d(04d / 16d, 12d / 16d)) //  |    |        |    |
      pipe.addTexCoord(Vec2d(12d / 16d, 12d / 16d)) // 07---08-------09---10
      pipe.addTexCoord(Vec2d(16d / 16d, 12d / 16d)) //       |        |
      pipe.addTexCoord(Vec2d(04d / 16d, 16d / 16d)) //       |        |
      pipe.addTexCoord(Vec2d(12d / 16d, 16d / 16d)) //      11-------12

      pipe.addNormal(Vec3d(01d, 00d, 00d)) // 1: X+
      pipe.addNormal(Vec3d(-1d, 00d, 00d)) // 2: X-
      pipe.addNormal(Vec3d(00d, 01d, 00d)) // 3: Y+
      pipe.addNormal(Vec3d(00d, -1d, 00d)) // 4: Y-
      pipe.addNormal(Vec3d(00d, 00d, 01d)) // 5: Z+
      pipe.addNormal(Vec3d(00d, 00d, -1d)) // 6: Z-

      var vi = 7

      if (is(i, 0x01)) {
        pipe.addVertex(Vec3d(08d / 16d, 04d / 16d, 04d / 16d))
        pipe.addVertex(Vec3d(08d / 16d, -4d / 16d, 04d / 16d))
        pipe.addVertex(Vec3d(08d / 16d, 04d / 16d, -4d / 16d))
        pipe.addVertex(Vec3d(08d / 16d, -4d / 16d, -4d / 16d))

        pipe.addFace(new BasicFace()
          .add(1, 5, 5)
          .add(vi + 1, 6, 5)
          .add(vi + 2, 10, 5)
          .add(3, 9, 5))

        pipe.addFace(new BasicFace()
          .add(3, 5, 4)
          .add(vi + 2, 6, 4)
          .add(vi + 4, 10, 4)
          .add(7, 9, 4))

        pipe.addFace(new BasicFace()
          .add(7, 5, 6)
          .add(vi + 4, 6, 6)
          .add(vi + 3, 10, 6)
          .add(5, 9, 6))

        pipe.addFace(new BasicFace()
          .add(1, 4, 3)
          .add(vi + 1, 3, 3)
          .add(vi + 3, 7, 3)
          .add(5, 8, 3))

        vi += 4
      } else {
        pipe.addFace(new BasicFace()
          .add(3, 4, 1)
          .add(1, 5, 1)
          .add(5, 9, 1)
          .add(7, 8, 1))
      }

      if (is(i, 0x02)) {
        pipe.addVertex(Vec3d(-8d / 16d, 04d / 16d, 04d / 16d))
        pipe.addVertex(Vec3d(-8d / 16d, -4d / 16d, 04d / 16d))
        pipe.addVertex(Vec3d(-8d / 16d, 04d / 16d, -4d / 16d))
        pipe.addVertex(Vec3d(-8d / 16d, -4d / 16d, -4d / 16d))

        pipe.addFace(new BasicFace()
          .add(2, 4, 5)
          .add(vi + 1, 3, 5)
          .add(vi + 2, 7, 5)
          .add(4, 8, 5))

        pipe.addFace(new BasicFace()
          .add(4, 4, 4)
          .add(vi + 2, 3, 4)
          .add(vi + 4, 7, 4)
          .add(8, 8, 4))

        pipe.addFace(new BasicFace()
          .add(8, 4, 6)
          .add(vi + 4, 3, 6)
          .add(vi + 3, 7, 6)
          .add(6, 8, 6))

        pipe.addFace(new BasicFace()
          .add(2, 5, 3)
          .add(vi + 1, 6, 3)
          .add(vi + 3, 10, 3)
          .add(6, 9, 3))

        vi += 4;
      } else {
        pipe.addFace(new BasicFace()
          .add(2, 4, 2)
          .add(4, 5, 2)
          .add(8, 9, 2)
          .add(6, 8, 2))
      }

      if (is(i, 0x4)) {
        pipe.addVertex(Vec3d(04d / 16d, 08d / 16d, 04d / 16d))
        pipe.addVertex(Vec3d(-4d / 16d, 08d / 16d, 04d / 16d))
        pipe.addVertex(Vec3d(04d / 16d, 08d / 16d, -4d / 16d))
        pipe.addVertex(Vec3d(-4d / 16d, 08d / 16d, -4d / 16d))

        pipe.addFace(new BasicFace()
          .add(2, 4, 5)
          .add(vi + 2, 1, 5)
          .add(vi + 1, 2, 5)
          .add(1, 5, 5))

        pipe.addFace(new BasicFace()
          .add(1, 5, 1)
          .add(vi + 1, 6, 1)
          .add(vi + 3, 10, 1)
          .add(5, 9, 1))

        pipe.addFace(new BasicFace()
          .add(5, 9, 6)
          .add(vi + 3, 12, 6)
          .add(vi + 4, 11, 6)
          .add(6, 8, 6))

        pipe.addFace(new BasicFace()
          .add(2, 4, 2)
          .add(vi + 2, 3, 2)
          .add(vi + 4, 7, 2)
          .add(6, 8, 2))

        vi += 4
      } else {
        pipe.addFace(new BasicFace()
          .add(1, 4, 3)
          .add(2, 5, 3)
          .add(6, 9, 3)
          .add(5, 8, 3))
      }
    }
  }

  def is(v: Int, byte: Int) = (v & byte) == byte
}