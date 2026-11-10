import { z } from "zod";

export const clientSchema = z.object({
  name: z.string().trim().min(1, "nameRequired").max(150, "nameTooLong"),
  email: z.string().trim().min(1, "emailRequired").pipe(z.email("emailInvalid")),
  phone: z
    .string()
    .trim()
    .regex(/^$|^\+?[0-9 ]{6,20}$/, "phoneInvalid")
    .optional(),
  address: z.string().trim().max(255, "addressTooLong").optional(),
});
