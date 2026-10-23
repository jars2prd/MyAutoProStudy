package com.myprog;

import java.util.Scanner;

public class _46CompoundFormation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int atomicMassA = scanner.nextInt();
        int atomicMassB = scanner.nextInt();
        int atomicMassC = scanner.nextInt();
        int atomicMassD = scanner.nextInt();
        int atomicMassX = scanner.nextInt();

        // Calculate molecular masses
        int molecularMassA = atomicMassA; // Monoatomic
        int molecularMassB = atomicMassB; // Monoatomic
        int molecularMassC = atomicMassC * 2; // Diatomic
        int molecularMassD = atomicMassD * 2; // Diatomic

        int maxMolecules = 0;

        // Iterate through all possible combinations of molecules
        // A, B, C, D represent the number of molecules of each type
        for (int numA = 0; numA * molecularMassA <= atomicMassX; numA++) {
            for (int numB = 0; numA * molecularMassA + numB * molecularMassB <= atomicMassX; numB++) {
                for (int numC = 0; numA * molecularMassA + numB * molecularMassB + numC * molecularMassC <= atomicMassX; numC++) {
                    for (int numD = 0; numA * molecularMassA + numB * molecularMassB + numC * molecularMassC + numD * molecularMassD <= atomicMassX; numD++) {

                        int currentMass = numA * molecularMassA + numB * molecularMassB + numC * molecularMassC + numD * molecularMassD;
                        int currentTotalMolecules = numA + numB + numC + numD;

                        if (currentMass == atomicMassX) {
                            maxMolecules = Math.max(maxMolecules, currentTotalMolecules);
                        }
                    }
                }
            }
        }
        System.out.println(maxMolecules);
        scanner.close();
    }
}