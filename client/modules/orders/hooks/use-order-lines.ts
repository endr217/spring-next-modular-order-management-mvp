import { useState } from "react";
import type { Product } from "@/modules/products/types";

export type OrderLineValue = { key: number; productId: string; quantity: string };

const emptyLine = (key: number): OrderLineValue => ({ key, productId: "", quantity: "1" });

export function useOrderLines(products: Product[]) {
  const [lines, setLines] = useState<OrderLineValue[]>([emptyLine(0)]);

  const add = () => setLines((current) => [...current, emptyLine(Math.max(-1, ...current.map((line) => line.key)) + 1)]);
  const remove = (key: number) => setLines((current) => current.filter((line) => line.key !== key));
  const update = (key: number, patch: Partial<OrderLineValue>) =>
    setLines((current) => current.map((line) => (line.key === key ? { ...line, ...patch } : line)));

  const total = lines.reduce((sum, line) => {
    const product = products.find((candidate) => String(candidate.id) === line.productId);
    return sum + (product ? product.price * (Number(line.quantity) || 0) : 0);
  }, 0);

  return { lines, add, remove, update, total };
}
