import { z } from "zod";

const item = z.object({
  productId: z.string().min(1, "productRequired").transform(Number),
  quantity: z
    .string()
    .trim()
    .regex(/^\d+$/, "quantityNotInteger")
    .transform(Number)
    .pipe(z.number().min(1, "quantityMin")),
});

export const orderSchema = z.object({
  clientId: z.string().min(1, "clientRequired").transform(Number),
  items: z.array(item).min(1, "itemsRequired"),
});
