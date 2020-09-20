package quakehazard

import org.scalajs.dom.raw.HTMLInputElement
import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.web.html._

import scala.scalajs.js
import scala.scalajs.js.annotation.{ JSGlobalScope, JSImport }

@JSImport("resources/App.css", JSImport.Default)
@js.native
object AppCSS extends js.Object

@JSGlobalScope
@js.native
object Globals extends js.Object {
  val areas: js.Array[Area] = js.native
}

@react class App extends Component {
  case class Props(messages: Messages)
  case class State(searchText: String, results: js.Array[Area])

  private val css = AppCSS

  def initialState: State = State("", js.Array())

  def normalizeSearchInput(text: String): String =
    text.trim.map {
      // Convert half-width to full-width digits
      case c if c >= '0' && c <= '9' => (c + 65248).toChar
      case c                         => c
    }

  def search(input: String): Unit = {
    val normalizedSearch = normalizeSearchInput(input)

    if (normalizedSearch.isEmpty) {
      setState(_.copy(searchText = normalizedSearch, results = js.Array()))
    } else {
      val results = Globals.areas.filter(_.fullName.contains(normalizedSearch)).take(500)
      setState(_.copy(searchText = normalizedSearch, results = results))
    }
  }

  def render(): ReactElement =
    div(className := "App")(
      div(id := "searchContainer")(
        input(
          id := "searchInput",
          `type` := "text",
          value := s"${state.searchText}",
          placeholder := "例）千代田区飯田橋１丁目",
          autoFocus := true,
          onChange := { e =>
            search(e.target.asInstanceOf[HTMLInputElement].value)
          }
        ),
        div(id := "resultsContainer", style := js.Dynamic.literal(display = if (state.results.isEmpty) "none" else "block"))(
          div(className := "resultsPadding"),
          div(id := "resultsMain")(
            table(id := "searchResults")(
              thead(
                tr(
                  th(),
                  th(colSpan := 3, className := "category")(props.messages.buildingCollapse),
                  th(colSpan := 3, className := "category")(props.messages.fireDamage),
                  th(colSpan := 2, className := "category")(props.messages.rescueDifficulty),
                  th(colSpan := 3, className := "category")(props.messages.overallRisk)
                ),
                tr(
                  th(className := "plainText")(props.messages.municipality),
                  th(title := props.messages.buildingsPerHectareDescription)(props.messages.buildingsPerHectare),
                  th(title := props.messages.rankDescription)(props.messages.rank),
                  th(title := props.messages.gradeDescription, className := "gradeText")(props.messages.grade),
                  th(title := props.messages.buildingsPerHectareDescription)(props.messages.buildingsPerHectare),
                  th(title := props.messages.rankDescription)(props.messages.rank),
                  th(title := props.messages.gradeDescription, className := "gradeText")(props.messages.grade),
                  th(title := props.messages.rankDescription)(props.messages.rank),
                  th(title := props.messages.gradeDescription, className := "gradeText")(props.messages.grade),
                  th(title := props.messages.buildingsPerHectareDescription)(props.messages.buildingsPerHectare),
                  th(title := props.messages.rankDescription)(props.messages.rank),
                  th(title := props.messages.gradeDescription, className := "gradeText")(props.messages.grade)
                )
              ),
              tbody(
                state.results.toSeq.map { area =>
                  tr(
                    td(className := "plainText")(area.fullName),
                    td(area.collapsedBuildingsPerHectare),
                    td(area.collapseSafetyRank),
                    td(className := s"gradeText grade${area.collapseSafetyGrade}")(area.collapseSafetyGrade),
                    td(area.fireDamagedBuildingsPerHectare),
                    td(area.fireSafetyRank),
                    td(className := s"gradeText grade${area.collapseSafetyGrade}")(area.fireSafetyGrade),
                    td(area.rescueDifficultyRank),
                    td(className := s"gradeText grade${area.collapseSafetyGrade}")(area.rescueDifficultyGrade),
                    td(area.totalDangerPerHectare),
                    td(area.totalDangerRank),
                    td(className := s"gradeText grade${area.collapseSafetyGrade}")(area.totalDangerGrade)
                  )
                }
              )
            )
          ),
          div(className := "resultsPadding")
        )
      ),
      MapComponent(
        ViewBox(10, 0, 790, 400),
        s => search(s)
      ),
      div(id := "footer")(
        "Source: ",
        a(href := "https://www.toshiseibi.metro.tokyo.lg.jp/bosai/chousa_6/home.htm")("東京都都市整備局")
      )
    )
}
