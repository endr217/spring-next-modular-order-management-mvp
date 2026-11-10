"use server";

import { refresh } from "next/cache";
import { toFormState } from "@/shared/forms/to-form-state";
import { apiFetch } from "@/shared/api/api-fetch";
import type { FormState } from "@/shared/forms/form-state";
import type { OrderStatus } from "@/modules/orders/types";

export async function changeStatus(id: number, status: OrderStatus): Promise<FormState> {
  try {
    await apiFetch(`/orders/${id}/status`, { method: "PATCH", body: { status } });
  } catch (error) {
    return toFormState(error);
  }
  refresh();
  return {};
}
