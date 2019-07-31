package uk.gov.hmrc.TestCases

import play.api.libs.json.Json.obj
import uk.gov.hmrc.decisionservice.models.Control

class ControlDecisionISpec extends BaseISpec {

  "Control Section" should {

    Seq(OldRuleEngine, NewRuleEngine).foreach { implicit engine =>

      s"POST ${engine.path}" should {

        "Scenario 1: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 2: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 3: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 4: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 5: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 6: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 7: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 8: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 9: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 10: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 11: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 12: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 13: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 14: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 15: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 16: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 17: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 18: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 19: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 20: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 21: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 22: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 23: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 24: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 25: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 26: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 27: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 28: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 29: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 30: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 31: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 32: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 33: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 34: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 35: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 36: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 37: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 38: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 39: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 40: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 41: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 42: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 43: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 44: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 45: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 46: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 47: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 48: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 49: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 50: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 51: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 52: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 53: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 54: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 55: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 56: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 57: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 58: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 59: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 60: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 61: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 62: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 63: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 64: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithPermission",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 65: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 66: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 67: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 68: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 69: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 70: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 71: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 72: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }



        /////



        "Scenario 73: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 74: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 75: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 76: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 77: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 78: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 79: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 80: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }


        /////



        "Scenario 81: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 82: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 83: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 84: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 85: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 86: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 87: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 88: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }


        /////



        "Scenario 89: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 90: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 91: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 92: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 93: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 94: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 95: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 96: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 97: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 98: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 99: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 100: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 101: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 102: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 103: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 104: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 105: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 106: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 107: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 108: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 109: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 110: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 111: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 112: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 113: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 114: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 115: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 116: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 117: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 118: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 119: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 120: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 121: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 122: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 123: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 124: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 125: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 126: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 127: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 128: return a 200, a HIGH for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 129: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 130: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 131: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 132: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 133: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 134: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 135: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 136: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 137: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"OUTOFIR35"""")
            result.body should include(""""result":"Outside IR35"""")
          }
        }

        "Scenario 138: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 139: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"OUTOFIR35"""")
            result.body should include(""""result":"Outside IR35"""")
          }
        }

        "Scenario 140: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 141: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"OUTOFIR35"""")
            result.body should include(""""result":"Outside IR35"""")
          }
        }

        "Scenario 142: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 143: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"OUTOFIR35"""")
            result.body should include(""""result":"Outside IR35"""")
          }
        }

        "Scenario 144: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 145: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 146: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 147: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 148: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 149: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 150: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 151: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 152: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 153: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 154: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 155: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 156: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 157: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 158: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 159: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 160: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 161: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 162: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 163: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 164: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 165: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 166: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 167: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 168: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 169: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 170: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 171: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 172: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 173: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 174: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 175: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 176: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "noWorkerInputAllowed",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 177: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 178: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 179: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 180: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 181: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 182: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 183: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 184: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "workerAgreeSchedule",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 185: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"OUTOFIR35"""")
            result.body should include(""""result":"Outside IR35"""")
          }
        }

        "Scenario 186: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 187: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"OUTOFIR35"""")
            result.body should include(""""result":"Outside IR35"""")
          }
        }

        "Scenario 188: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "workerDecideSchedule",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 189: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"OUTOFIR35"""")
            result.body should include(""""result":"Outside IR35"""")
          }
        }

        "Scenario 190: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 191: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"OUTOFIR35"""")
            result.body should include(""""result":"Outside IR35"""")
          }
        }

        "Scenario 192: return a 200, a MEDIUM for control" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
              Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
              Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
              Control.workerDecideWhere -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }
      }
    }
  }
}
