import { z } from "zod";

const price = z
  .string()
  .trim()
  .regex(/^-?\d+([.,]\d+)?$/, "priceNotNumber")
  .regex(/^-?\d+([.,]\d{1,2})?$/, "priceScale")
  .transform((value) => Number(value.replace(",", ".")))
  .pipe(z.number().min(0, "priceNegative").max(99999999.99, "priceTooHigh"));

const stock = z
  .string()
  .trim()
  .min(1, "stockNotNumber")
  .regex(/^-?\d+$/, "quantityNotInteger")
  .transform(Number)
  .pipe(z.number().min(0, "stockNegative").max(1_000_000, "stockTooHigh"));

export const productSchema = z.object({
  name: z.string().trim().min(1, "productNameRequired").max(150, "productNameTooLong"),
  price,
  stock,
  categoryId: z.string().min(1, "categoryRequired").transform(Number),
});
