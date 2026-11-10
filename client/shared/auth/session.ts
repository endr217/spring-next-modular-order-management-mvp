import "server-only";
import { cookies } from "next/headers";
import { TOKEN_COOKIE } from "@/shared/auth/token-cookie";

export async function getToken() {
  return (await cookies()).get(TOKEN_COOKIE)?.value;
}

export async function saveToken(token: string, maxAge: number) {
  (await cookies()).set(TOKEN_COOKIE, token, {
    httpOnly: true,
    sameSite: "lax",
    secure: process.env.NODE_ENV === "production",
    path: "/",
    maxAge,
  });
}

export async function clearToken() {
  (await cookies()).delete(TOKEN_COOKIE);
}
