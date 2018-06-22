export interface Medicine{
    id?: number;
    components?: Medicine[];
    medicineType?: string;
    name: string;
}