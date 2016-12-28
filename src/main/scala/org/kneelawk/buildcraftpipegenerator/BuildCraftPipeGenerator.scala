package org.kneelawk.buildcraftpipegenerator

import java.io.File
import java.io.FileOutputStream

import org.kneelawk.wavefronttools.BasicFace
import org.kneelawk.wavefronttools.BasicMesh
import org.kneelawk.wavefronttools.ObjWriter
import org.kneelawk.wavefronttools.Vec2d
import org.kneelawk.wavefronttools.Vec3d
import org.kneelawk.wavefronttools.BasicMeshContext
import org.kneelawk.wavefronttools.Mat4d
import org.kneelawk.wavefronttools.Vec4d

object BuildCraftPipeGenerator {
  def main(args: Array[String]) {
    val outputDir = new File("output")
    if (!outputDir.exists()) outputDir.mkdirs()

    val bigFile = new File(outputDir, "full.obj")

    val bigOut = new ObjWriter(new FileOutputStream(bigFile))
    var bigCtx = new BasicMeshContext

    for (i <- 0 until 0x40) {
      var name = "pipe"

      if (is(i, 0x01)) {
        name += "X+"
      } else {
        name += "__"
      }
      if (is(i, 0x02)) {
        name += "X-"
      } else {
        name += "__"
      }
      if (is(i, 0x04)) {
        name += "Y+"
      } else {
        name += "__"
      }
      if (is(i, 0x08)) {
        name += "Y-"
      } else {
        name += "__"
      }
      if (is(i, 0x10)) {
        name += "Z+"
      } else {
        name += "__"
      }
      if (is(i, 0x20)) {
        name += "Z-"
      } else {
        name += "__"
      }

      val pipeFile = new File(outputDir, name + ".obj")
      val pipeOut = new ObjWriter(new FileOutputStream(pipeFile))

      val ctx = new BasicMeshContext
      val pipe = new BasicMesh(name)
      val v1 = ctx.addVertex(Vec3d(04d / 16d, 04d / 16d, 04d / 16d)) //
      val v2 = ctx.addVertex(Vec3d(-4d / 16d, 04d / 16d, 04d / 16d)) //   2-------1
      val v3 = ctx.addVertex(Vec3d(04d / 16d, -4d / 16d, 04d / 16d)) //  /|      /|
      val v4 = ctx.addVertex(Vec3d(-4d / 16d, -4d / 16d, 04d / 16d)) // 4-------3 | z+
      val v5 = ctx.addVertex(Vec3d(04d / 16d, 04d / 16d, -4d / 16d)) // | |     | | |
      val v6 = ctx.addVertex(Vec3d(-4d / 16d, 04d / 16d, -4d / 16d)) // | 6-----|-5 | y+
      val v7 = ctx.addVertex(Vec3d(04d / 16d, -4d / 16d, -4d / 16d)) // |/      |/  |/
      val v8 = ctx.addVertex(Vec3d(-4d / 16d, -4d / 16d, -4d / 16d)) // 8-------7   0-----x+

      // Texture Coordinates
      //      01-------02
      //       |        |
      //       |        |
      // 03---04-------05---06
      //  |    |        |    |
      //  |    |        |    |
      //  |    |        |    |
      //  |    |        |    |
      // 07---08-------09---10
      //       |        |
      //       |        |
      //      11-------12
      //
      // Vertex Normals
      // 1: X+
      // 2: X-
      // 3: Y+
      // 4: Y-
      // 5: Z+
      // 6: Z-

      val vt01 = ctx.addTexCoord(Vec2d(04d / 16d, 00d / 16d))
      val vt02 = ctx.addTexCoord(Vec2d(12d / 16d, 00d / 16d))
      val vt03 = ctx.addTexCoord(Vec2d(00d / 16d, 04d / 16d))
      val vt04 = ctx.addTexCoord(Vec2d(04d / 16d, 04d / 16d))
      val vt05 = ctx.addTexCoord(Vec2d(12d / 16d, 04d / 16d))
      val vt06 = ctx.addTexCoord(Vec2d(16d / 16d, 04d / 16d))
      val vt07 = ctx.addTexCoord(Vec2d(00d / 16d, 12d / 16d))
      val vt08 = ctx.addTexCoord(Vec2d(04d / 16d, 12d / 16d))
      val vt09 = ctx.addTexCoord(Vec2d(12d / 16d, 12d / 16d))
      val vt10 = ctx.addTexCoord(Vec2d(16d / 16d, 12d / 16d))
      val vt11 = ctx.addTexCoord(Vec2d(04d / 16d, 16d / 16d))
      val vt12 = ctx.addTexCoord(Vec2d(12d / 16d, 16d / 16d))

      val vn1 = ctx.addNormal(Vec3d(01d, 00d, 00d))
      val vn2 = ctx.addNormal(Vec3d(-1d, 00d, 00d))
      val vn3 = ctx.addNormal(Vec3d(00d, 01d, 00d))
      val vn4 = ctx.addNormal(Vec3d(00d, -1d, 00d))
      val vn5 = ctx.addNormal(Vec3d(00d, 00d, 01d))
      val vn6 = ctx.addNormal(Vec3d(00d, 00d, -1d))

      if (is(i, 0x01)) {
        // X+
        val ev1 = ctx.addVertex(Vec3d(08d / 16d, 04d / 16d, 04d / 16d))
        val ev2 = ctx.addVertex(Vec3d(08d / 16d, -4d / 16d, 04d / 16d))
        val ev3 = ctx.addVertex(Vec3d(08d / 16d, 04d / 16d, -4d / 16d))
        val ev4 = ctx.addVertex(Vec3d(08d / 16d, -4d / 16d, -4d / 16d))

        pipe.addFace(new BasicFace()
          .add(v1, vt05, vn5)
          .add(ev1, vt06, vn5)
          .add(ev2, vt10, vn5)
          .add(v3, vt09, vn5))

        pipe.addFace(new BasicFace()
          .add(v3, vt05, vn4)
          .add(ev2, vt06, vn4)
          .add(ev4, vt10, vn4)
          .add(v7, vt09, vn4))

        pipe.addFace(new BasicFace()
          .add(v7, vt05, vn6)
          .add(ev4, vt06, vn6)
          .add(ev3, vt10, vn6)
          .add(v5, vt09, vn6))

        pipe.addFace(new BasicFace()
          .add(v1, vt04, vn3)
          .add(ev1, vt03, vn3)
          .add(ev3, vt07, vn3)
          .add(v5, vt08, vn3))
      } else {
        pipe.addFace(new BasicFace()
          .add(v3, vt04, vn1)
          .add(v1, vt05, vn1)
          .add(v5, vt09, vn1)
          .add(v7, vt08, vn1))
      }

      if (is(i, 0x02)) {
        // X-
        val ev1 = ctx.addVertex(Vec3d(-8d / 16d, 04d / 16d, 04d / 16d))
        val ev2 = ctx.addVertex(Vec3d(-8d / 16d, -4d / 16d, 04d / 16d))
        val ev3 = ctx.addVertex(Vec3d(-8d / 16d, 04d / 16d, -4d / 16d))
        val ev4 = ctx.addVertex(Vec3d(-8d / 16d, -4d / 16d, -4d / 16d))

        pipe.addFace(new BasicFace()
          .add(v2, vt04, vn5)
          .add(ev1, vt03, vn5)
          .add(ev2, vt07, vn5)
          .add(v4, vt08, vn5))

        pipe.addFace(new BasicFace()
          .add(v4, vt04, vn4)
          .add(ev2, vt03, vn4)
          .add(ev4, vt07, vn4)
          .add(v8, vt08, vn4))

        pipe.addFace(new BasicFace()
          .add(v8, vt04, vn6)
          .add(ev4, vt03, vn6)
          .add(ev3, vt07, vn6)
          .add(v6, vt08, vn6))

        pipe.addFace(new BasicFace()
          .add(v2, vt05, vn3)
          .add(ev1, vt06, vn3)
          .add(ev3, vt10, vn3)
          .add(v6, vt09, vn3))
      } else {
        pipe.addFace(new BasicFace()
          .add(v2, vt04, vn2)
          .add(v4, vt05, vn2)
          .add(v8, vt09, vn2)
          .add(v6, vt08, vn2))
      }

      if (is(i, 0x4)) {
        // Y+
        val ev1 = ctx.addVertex(Vec3d(04d / 16d, 08d / 16d, 04d / 16d))
        val ev2 = ctx.addVertex(Vec3d(-4d / 16d, 08d / 16d, 04d / 16d))
        val ev3 = ctx.addVertex(Vec3d(04d / 16d, 08d / 16d, -4d / 16d))
        val ev4 = ctx.addVertex(Vec3d(-4d / 16d, 08d / 16d, -4d / 16d))

        pipe.addFace(new BasicFace()
          .add(v2, vt04, vn5)
          .add(ev2, vt01, vn5)
          .add(ev1, vt02, vn5)
          .add(v1, vt05, vn5))

        pipe.addFace(new BasicFace()
          .add(v1, vt05, vn1)
          .add(ev1, vt06, vn1)
          .add(ev3, vt10, vn1)
          .add(v5, vt09, vn1))

        pipe.addFace(new BasicFace()
          .add(v5, vt09, vn6)
          .add(ev3, vt12, vn6)
          .add(ev4, vt11, vn6)
          .add(v6, vt08, vn6))

        pipe.addFace(new BasicFace()
          .add(v2, vt04, vn2)
          .add(ev2, vt03, vn2)
          .add(ev4, vt07, vn2)
          .add(v6, vt08, vn2))
      } else {
        pipe.addFace(new BasicFace()
          .add(v1, vt04, vn3)
          .add(v2, vt05, vn3)
          .add(v6, vt09, vn3)
          .add(v5, vt08, vn3))
      }

      if (is(i, 0x8)) {
        // Y-
        val ev1 = ctx.addVertex(Vec3d(04d / 16d, -8d / 16d, 04d / 16d))
        val ev2 = ctx.addVertex(Vec3d(-4d / 16d, -8d / 16d, 04d / 16d))
        val ev3 = ctx.addVertex(Vec3d(04d / 16d, -8d / 16d, -4d / 16d))
        val ev4 = ctx.addVertex(Vec3d(-4d / 16d, -8d / 16d, -4d / 16d))

        pipe.addFace(new BasicFace()
          .add(v3, vt09, vn5)
          .add(ev1, vt12, vn5)
          .add(ev2, vt11, vn5)
          .add(v4, vt08, vn5))

        pipe.addFace(new BasicFace()
          .add(v3, vt04, vn1)
          .add(ev1, vt03, vn1)
          .add(ev3, vt07, vn1)
          .add(v7, vt08, vn1))

        pipe.addFace(new BasicFace()
          .add(v7, vt05, vn6)
          .add(ev3, vt02, vn6)
          .add(ev4, vt01, vn6)
          .add(v8, vt04, vn6))

        pipe.addFace(new BasicFace()
          .add(v4, vt05, vn2)
          .add(ev2, vt06, vn2)
          .add(ev4, vt10, vn2)
          .add(v8, vt09, vn2))
      } else {
        pipe.addFace(new BasicFace()
          .add(v4, vt04, vn4)
          .add(v3, vt05, vn4)
          .add(v7, vt09, vn4)
          .add(v8, vt08, vn4))
      }

      if (is(i, 0x10)) {
        // Z+
        val ev1 = ctx.addVertex(Vec3d(04d / 16d, 04d / 16d, 08d / 16d))
        val ev2 = ctx.addVertex(Vec3d(-4d / 16d, 04d / 16d, 08d / 16d))
        val ev3 = ctx.addVertex(Vec3d(04d / 16d, -4d / 16d, 08d / 16d))
        val ev4 = ctx.addVertex(Vec3d(-4d / 16d, -4d / 16d, 08d / 16d))

        pipe.addFace(new BasicFace()
          .add(v4, vt04, vn4)
          .add(ev4, vt01, vn4)
          .add(ev3, vt02, vn4)
          .add(v3, vt05, vn4))

        pipe.addFace(new BasicFace()
          .add(v3, vt04, vn1)
          .add(ev3, vt01, vn1)
          .add(ev1, vt02, vn1)
          .add(v1, vt05, vn1))

        pipe.addFace(new BasicFace()
          .add(v1, vt04, vn3)
          .add(ev1, vt01, vn3)
          .add(ev2, vt02, vn3)
          .add(v2, vt05, vn3))

        pipe.addFace(new BasicFace()
          .add(v2, vt04, vn2)
          .add(ev2, vt01, vn2)
          .add(ev4, vt02, vn2)
          .add(v4, vt05, vn2))
      } else {
        pipe.addFace(new BasicFace()
          .add(v2, vt04, vn5)
          .add(v1, vt05, vn5)
          .add(v3, vt09, vn5)
          .add(v4, vt08, vn5))
      }

      if (is(i, 0x20)) {
        // Z-
        val ev1 = ctx.addVertex(Vec3d(04d / 16d, 04d / 16d, -8d / 16d))
        val ev2 = ctx.addVertex(Vec3d(-4d / 16d, 04d / 16d, -8d / 16d))
        val ev3 = ctx.addVertex(Vec3d(04d / 16d, -4d / 16d, -8d / 16d))
        val ev4 = ctx.addVertex(Vec3d(-4d / 16d, -4d / 16d, -8d / 16d))

        pipe.addFace(new BasicFace()
          .add(v7, vt09, vn4)
          .add(ev3, vt12, vn4)
          .add(ev4, vt11, vn4)
          .add(v8, vt08, vn4))

        pipe.addFace(new BasicFace()
          .add(v5, vt09, vn1)
          .add(ev1, vt12, vn1)
          .add(ev3, vt11, vn1)
          .add(v7, vt08, vn1))

        pipe.addFace(new BasicFace()
          .add(v6, vt09, vn3)
          .add(ev2, vt12, vn3)
          .add(ev1, vt11, vn3)
          .add(v5, vt08, vn3))

        pipe.addFace(new BasicFace()
          .add(v8, vt09, vn2)
          .add(ev4, vt12, vn2)
          .add(ev2, vt11, vn2)
          .add(v6, vt08, vn2))
      } else {
        pipe.addFace(new BasicFace()
          .add(v8, vt04, vn6)
          .add(v7, vt05, vn6)
          .add(v5, vt09, vn6)
          .add(v6, vt08, vn6))
      }

      ctx.addMesh(pipe)

      pipeOut.writeMeshes(ctx)

      bigCtx += ctx.transform(Mat4d.translate(Vec4d((i % 4) - 1.5, ((i / 4).toInt % 4) - 1.5, ((i / 16).toInt % 4) - 1.5, 1)))
    }

    bigOut.writeMeshes(bigCtx)
  }

  def is(v: Int, byte: Int) = (v & byte) == byte
}