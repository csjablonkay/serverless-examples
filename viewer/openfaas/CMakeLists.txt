cmake_minimum_required(VERSION 2.8.12)
project(viewer)
find_package(Poco)
find_package(hiredis)
find_program(DOCKER docker)

set (CMAKE_CXX_STANDARD 11)
add_definitions("-D_GLIBCXX_USE_CXX11_ABI=0")

include_directories(${Poco_INCLUDE_DIRS})
include_directories(${hiredis_INCLUDE_DIRS})

add_executable(${PROJECT_NAME} src/viewer.cpp)
target_link_libraries(${PROJECT_NAME} ${Poco_LIBRARIES} ${hiredis_LIBRARIES})
add_custom_target(docker_build COMMAND ${DOCKER} build -t csjablonkay/viewer-openfaas:latest ..)
