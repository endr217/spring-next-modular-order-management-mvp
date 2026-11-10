"use server";

import { redirect } from "next/navigation";
import { parseForm } from "@/shared/forms/parse-form";
import { toFormState } from "@/shared/forms/to-form-state";
import { apiFetch } from "@/shared/api/api-fetch";
import type { FormState } from "@/shared/forms/form-state";
import type { Order } from "@/modules/orders/types";
import { orderSchema } from "@/modules/orders/schema";

export async function createOrder(_: FormState, formData: FormData): Promise<FormState> {
  const quantities = formData.getAll("quantity");
  const items = formData.getAll("productId").map((productId, index) => ({ productId, quantity: quantities[index] }));
  const parsed = await parseForm(orderSchema, { clientId: formData.get("clientId"), items });
  if (!parsed.success) return { errors: parsed.errors };

  let order: Order;
  try {
    order = await apiFetch<Order>("/orders", { method: "POST", body: parsed.data });
  } catch (error) {
    return toFormState(error);
  }
  redirect(`/orders/${order.id}`);
}
