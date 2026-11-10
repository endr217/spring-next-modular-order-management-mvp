import "server-only";
import { apiFetch } from "@/shared/api/api-fetch";
import type { Category } from "@/modules/products/types";

export function getCategories() {
  return apiFetch<Category[]>("/categories");
}
