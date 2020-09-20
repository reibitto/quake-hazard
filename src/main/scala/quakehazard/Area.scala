package quakehazard

import scala.scalajs.js

@js.native
trait Area extends js.Object {
  def municipality: String
  def district: String
  def fullName: String
  def collapsedBuildingsPerHectare: Double
  def collapseSafetyRank: Int
  def collapseSafetyGrade: Int
  def fireDamagedBuildingsPerHectare: Double
  def fireSafetyRank: Int
  def fireSafetyGrade: Int
  def rescueDifficultyRank: Int
  def rescueDifficultyGrade: Int
  def totalDangerPerHectare: Double
  def totalDangerRank: Int
  def totalDangerGrade: Int
}
