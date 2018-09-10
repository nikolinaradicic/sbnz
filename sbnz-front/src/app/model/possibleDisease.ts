import { Disease } from "./disease";

export interface PossibleDisease{
    disease: Disease;
    numSymptoms: number;
    numSpecSymptoms: number;
}