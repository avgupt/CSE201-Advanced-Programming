# Problem Statement

Design a software application to simplify the procedure of admitting patients identified to be infected by 
CoronaVirus to various Health Care Institutes. The health coordinator at a particular camp will use your software to
onboard newly identified patients and allocate them to various healthcare institutes that have
available beds with them. Since this has been asked in short notice, a command-line interface
would do the job. Detailed description of this software is as mentioned below:

Each patient can register themselves only once. They must be registered with *name*, *age*,
*oxygen levels (90 – 100)* and *body temperature*. A *unique ID* must also be assigned by the
application to each patient automatically. Once a patient has been admitted to a health care
institute, your software must store the advised number of days for recovery against the patient’s
ID.

Every health care institute can have different criteria to handle the patients. Due to expertise
restriction, certain institutes might not be able to handle critically ill patients. Hence these criteria
can be used to shortlist the patients who can be admitted to particular institutes.
There can be two cases:
   1. Firstly fill the patients based on the oxygen levels which are accepted by the health care institute.
   2. If there are still available beds, fill the patients based on the criteria for body temperature.
Note: They are independent. Check only oxygen level criteria first, then if needed check for body temperature criteria separately.

If any two patients are having the same oxygen or same temperature levels, either of the
patients could be admitted.

A Health Care institute should have a status as **OPEN** or **CLOSED**, based on the available beds
with them. Once they have admitted enough patients to fill in the vacant beds, their status
should change to **CLOSED**.

Once any healthcare institute has filled all its available beds, the healthcare account status is
changed to **CLOSED**. Later, the user must be able to delete the account of the healthcare
institute which is closed. Similarly, for a patient, once they are admitted to an institute, they
should not be re-admitted anywhere else by mistake. The application user must be able to
delete the admitted patient account too at a later point in time.

Your application must exit once all the patients have been admitted.
