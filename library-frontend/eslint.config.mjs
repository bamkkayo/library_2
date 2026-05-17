import { dirname } from "path";
import { fileURLToPath } from "url";
import { FlatCompat } from "@eslint/eslintrc";

const __filename = fileURLToPath(import.meta.url);
const __dirname = dirname(__filename);

const compat = new FlatCompat({
  baseDirectory: __dirname,
});

const eslintConfig = [
  // Next.js 핵심 웹 바이탈 및 기본 권장 규칙 확장 적용
  ...compat.extends("next/core-web-vitals", "next/typescript"),

  // 검사에서 제외할 빌드 및 환경 파일 폴더 지정
  {
    ignores: [
      ".next/**",
      "out/**",
      "build/**",
      "next-env.d.ts"
    ],
  },
];

export default eslintConfig;