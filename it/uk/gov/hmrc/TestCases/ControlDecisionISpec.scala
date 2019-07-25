package uk.gov.hmrc.TestCases

import play.api.libs.json.Json.obj

class ControlDecisionISpec extends BaseISpec {

  "Control Section" should {

    Seq(OldRuleEngine, NewRuleEngine).foreach { implicit engine =>

      s"POST ${engine.path}" should {

        "Scenario 1: return a 200, a OUTOFIR35 for control and Outside IR35 result" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              "engagerMovingWorker" -> "cannotMoveWorkerWithoutNewAgreement",
              "workerDecidingHowWorkIsDone" -> "workerDecidesWithoutInput",
              "whenWorkHasToBeDone" -> "workerDecideSchedule",
              "workerDecideWhere" -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"OUTOFIR35"""")
            result.body should include(""""result":"Outside IR35"""")
          }
        }

        "Scenario 2: return a 200, a OUTOFIR35 for control and Outside IR35 result" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              "engagerMovingWorker" -> "cannotMoveWorkerWithoutNewAgreement",
              "workerDecidingHowWorkIsDone" -> "workerDecidesWithoutInput",
              "whenWorkHasToBeDone" -> "workerDecideSchedule",
              "workerDecideWhere" -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"OUTOFIR35"""")
            result.body should include(""""result":"Outside IR35"""")
          }
        }

        "Scenario 3: return a 200, a OUTOFIR35 for control and Outside IR35 result" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              "engagerMovingWorker" -> "cannotMoveWorkerWithoutNewAgreement",
              "workerDecidingHowWorkIsDone" -> "workerDecidesWithoutInput",
              "whenWorkHasToBeDone" -> "noScheduleRequiredOnlyDeadlines",
              "workerDecideWhere" -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"OUTOFIR35"""")
            result.body should include(""""result":"Outside IR35"""")
          }
        }

        "Scenario 4: return a 200, a OUTOFIR35 for control and Outside IR35 result" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              "engagerMovingWorker" -> "cannotMoveWorkerWithoutNewAgreement",
              "workerDecidingHowWorkIsDone" -> "workerDecidesWithoutInput",
              "whenWorkHasToBeDone" -> "noScheduleRequiredOnlyDeadlines",
              "workerDecideWhere" -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"OUTOFIR35"""")
            result.body should include(""""result":"Outside IR35"""")
          }
        }

        "Scenario 5: return a 200, a OUTOFIR35 for control and Outside IR35 result" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              "engagerMovingWorker" -> "cannotMoveWorkerWithoutNewAgreement",
              "workerDecidingHowWorkIsDone" -> "workerFollowStrictEmployeeProcedures",
              "whenWorkHasToBeDone" -> "workerDecideSchedule",
              "workerDecideWhere" -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"OUTOFIR35"""")
            result.body should include(""""result":"Outside IR35"""")
          }
        }

        "Scenario 6: return a 200, a OUTOFIR35 for control and Outside IR35 result" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              "engagerMovingWorker" -> "cannotMoveWorkerWithoutNewAgreement",
              "workerDecidingHowWorkIsDone" -> "workerFollowStrictEmployeeProcedures",
              "whenWorkHasToBeDone" -> "workerDecideSchedule",
              "workerDecideWhere" -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"OUTOFIR35"""")
            result.body should include(""""result":"Outside IR35"""")
          }
        }

        "Scenario 7: return a 200, a OUTOFIR35 for control and Outside IR35 result" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              "engagerMovingWorker" -> "cannotMoveWorkerWithoutNewAgreement",
              "workerDecidingHowWorkIsDone" -> "workerFollowStrictEmployeeProcedures",
              "whenWorkHasToBeDone" -> "noScheduleRequiredOnlyDeadlines",
              "workerDecideWhere" -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"OUTOFIR35"""")
            result.body should include(""""result":"Outside IR35"""")
          }
        }

        "Scenario 8: return a 200, a OUTOFIR35 for control and Outside IR35 result" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              "engagerMovingWorker" -> "cannotMoveWorkerWithoutNewAgreement",
              "workerDecidingHowWorkIsDone" -> "workerFollowStrictEmployeeProcedures",
              "whenWorkHasToBeDone" -> "noScheduleRequiredOnlyDeadlines",
              "workerDecideWhere" -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"OUTOFIR35"""")
            result.body should include(""""result":"Outside IR35"""")
          }
        }

        "Scenario 9: return a 200, a HIGH for control and Unknown" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              "engagerMovingWorker" -> "canMoveWorkerWithPermission",
              "workerDecidingHowWorkIsDone" -> "workerDecidesWithoutInput",
              "whenWorkHasToBeDone" -> "scheduleDecidedForWorker",
              "workerDecideWhere" -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 10: return a 200, a HIGH for control and Unknown" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              "engagerMovingWorker" -> "canMoveWorkerWithPermission",
              "workerDecidingHowWorkIsDone" -> "workerAgreeWithOthers",
              "whenWorkHasToBeDone" -> "scheduleDecidedForWorker",
              "workerDecideWhere" -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 11: return a 200, a HIGH for control and Unknown" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              "engagerMovingWorker" -> "canMoveWorkerWithPermission",
              "workerDecidingHowWorkIsDone" -> "workerAgreeWithOthers",
              "whenWorkHasToBeDone" -> "scheduleDecidedForWorker",
              "workerDecideWhere" -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 12: return a 200, a HIGH for control and Unknown" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              "engagerMovingWorker" -> "canMoveWorkerWithPermission",
              "workerDecidingHowWorkIsDone" -> "noWorkerInputAllowed",
              "whenWorkHasToBeDone" -> "noScheduleRequiredOnlyDeadlines",
              "workerDecideWhere" -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 13: return a 200, a HIGH for control and Unknown" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              "engagerMovingWorker" -> "canMoveWorkerWithPermission",
              "workerDecidingHowWorkIsDone" -> "workerAgreeWithOthers",
              "whenWorkHasToBeDone" -> "workerAgreeSchedule",
              "workerDecideWhere" -> "workerCannotChoose"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"HIGH"""")
          }
        }

        "Scenario 14: return a 200, a MEDIUM for control and Unknown" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              "engagerMovingWorker" -> "canMoveWorkerWithPermission",
              "workerDecidingHowWorkIsDone" -> "workerDecidesWithoutInput",
              "whenWorkHasToBeDone" -> "scheduleDecidedForWorker",
              "workerDecideWhere" -> "workerChooses"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 15: return a 200, a MEDIUM for control and Unknown" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              "engagerMovingWorker" -> "canMoveWorkerWithPermission",
              "workerDecidingHowWorkIsDone" -> "workerDecidesWithoutInput",
              "whenWorkHasToBeDone" -> "scheduleDecidedForWorker",
              "workerDecideWhere" -> "noLocationRequired"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 16: return a 200, a MEDIUM for control and Unknown" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              "engagerMovingWorker" -> "canMoveWorkerWithPermission",
              "workerDecidingHowWorkIsDone" -> "workerDecidesWithoutInput",
              "whenWorkHasToBeDone" -> "scheduleDecidedForWorker",
              "workerDecideWhere" -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 17: return a 200, a MEDIUM for control and Unknown" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              "engagerMovingWorker" -> "cannotMoveWorkerWithoutNewAgreement",
              "workerDecidingHowWorkIsDone" -> "workerFollowStrictEmployeeProcedures",
              "whenWorkHasToBeDone" -> "noScheduleRequiredOnlyDeadlines",
              "workerDecideWhere" -> "workerAgreeWithOthers"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""control":"MEDIUM"""")
          }
        }

        "Scenario 18: return a 200, a MEDIUM for control and Unknown" in {

          lazy val res = postRequest(engine.path,
            interview(control = obj(
              "engagerMovingWorker" -> "cannotMoveWorkerWithoutNewAgreement",
              "workerDecidingHowWorkIsDone" -> "workerAgreeWithOthers",
              "whenWorkHasToBeDone" -> "workerAgreeSchedule",
              "workerDecideWhere" -> "workerAgreeWithOthers"
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
