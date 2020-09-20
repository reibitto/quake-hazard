package quakehazard

trait Messages {
  def municipality: String
  def buildingsPerHectare: String
  def buildingsPerHectareDescription: String
  def rank: String
  def rankDescription: String
  def grade: String
  def gradeDescription: String
  def buildingCollapse: String
  def fireDamage: String
  def rescueDifficulty: String
  def overallRisk: String

}

object Messages {
  lazy val en: Messages = new Messages {
    val municipality: String                   = "Municipality"
    val buildingsPerHectare: String            = "Bld / ha"
    val buildingsPerHectareDescription: String = "Buildings per hectare (10,000 m²)"
    val rank: String                           = "Rank"
    val rankDescription: String                = "Rank (1 = worst, 5177 = best)"
    val grade: String                          = "Grade"
    val gradeDescription: String               = "Grade (1 = best, 5 = worst)"
    val buildingCollapse: String               = "Building Collapse"
    val fireDamage: String                     = "Fire Damage"
    val rescueDifficulty: String               = "Rescue Difficulty"
    val overallRisk: String                    = "Overall Risk"
  }

  lazy val ja: Messages = new Messages {
    val municipality: String                   = "区市町"
    val buildingsPerHectare: String            = "棟 / ha"
    val buildingsPerHectareDescription: String = "棟 / ha (10,000 m²)"
    val rank: String                           = "順位"
    val rankDescription: String                = "順位 (1 = 危険性が高い, 5177 = 危険性が低い)"
    val grade: String                          = "ランク"
    val gradeDescription: String               = "危険度ランク (1 = 危険性が低い, 5 = 危険性が高い)"
    val buildingCollapse: String               = "建物倒壊危険度"
    val fireDamage: String                     = "火災危険度"
    val rescueDifficulty: String               = "災害時活動困難度"
    val overallRisk: String                    = "総合危険度"
  }
}
