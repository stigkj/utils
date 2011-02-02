import collection.immutable.TreeMap
import collection.mutable.BitSet
import java.lang.String
import java.util.NoSuchElementException

/**
 * @author Stig Kleppe-Jorgensen, 2011.02.02
 * @fixme add description
 */

val columns: Array[Iterator[String]] = args.map ( arg => io.Source.fromFile(arg).getLines )

var m = new TreeMap[String, RowWithColumns]

columns.toList.view.zipWithIndex foreach { case (column, index) =>
  column.foreach{n =>
    val name: String = n.trim
    var rwc: RowWithColumns = null

    try {
      rwc = m(name)
    }
    catch {
      case e: NoSuchElementException => {
        rwc = new RowWithColumns(name)
        m += (name -> rwc)
      }
    }

    rwc.setColumn(index)
  }
}

m.values.foreach(println(_))



class RowWithColumns(n: String) {
  val name = n;
  var columns = BitSet()

  def setColumn(i: Int) {
    columns += i
  }

  override def toString: String = {
    var s = ""

    if (columns(0)) {
      s += name + ";"
    } else {
      s += ";"
    }
    if (columns(1)) {
      s += name + ";"
    } else {
      s += ";"
    }
    if (columns(2)) {
      s += name + ";"
    } else {
      s += ";"
    }

    return s
  }
}