import { Symptom } from "./symptom";

export interface Disease{
    name: string;
    id?: number;
    diseaseType: string;
    symptoms: Symptom[]
}