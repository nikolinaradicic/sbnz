import { Symptom } from "./symptom";
import { Disease } from "./disease";
import { Medicine } from "./medicine";

export interface MedicalRecord{
    id?: number;
    disease?: Disease;
    symptoms?: Symptom[];
    medicine?: Medicine[]
}